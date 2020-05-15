package loadtest

import com.github.phisgr.gatling.grpc.Predef._
import com.github.phisgr.gatling.pb._
import geoip2.geoip2.Message.Locale
import geoip2.geoip2._
import io.gatling.core.Predef._
import io.gatling.core.Predef.{stringToExpression => _, _}
import io.gatling.core.session.Expression
import io.grpc.Status

import scala.util.Random
import scala.concurrent.duration._
import io.grpc.CallOptions

class MMDBServerSimulation extends Simulation {
  val ip = sys.env.getOrElse("MMDB_SERVER_HOST", "localhost")
  val port = sys.env.getOrElse("MMDB_SERVER_PORT", "50000")
  val mcb = managedChannelBuilder(ip, port.toInt).usePlaintext()

  val proto = grpc(mcb).warmUpCall(GeoIpGrpc.METHOD_LOOKUP, Message("126.203.22.11")).shareChannel

  def o = Random.nextInt(224) + 30
  val feeder = Iterator.continually(Map("ip" -> s"$o.$o.$o.$o"))

  val scn =
    scenario(s"mmdb")
      .feed(feeder)
      .exec(
        grpc("lookup")
          .rpc(GeoIpGrpc.METHOD_LOOKUP)
          .payload(
            Message.defaultInstance.updateExpr(
              _.ip :~ $("ip"),
              _.locales :+~ (_ => Locale.ENGLISH)
            )
          )
      )

  setUp(
    scn.inject(constantConcurrentUsers(10).during(60.seconds))
  ).protocols(proto)
}
