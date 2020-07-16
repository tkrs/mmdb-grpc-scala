package mmdbgrpc.gatling.loadtest

import com.github.phisgr.gatling.grpc.Predef._
import com.github.phisgr.gatling.pb._
import geoip2.geoip2.Message.Locale
import geoip2.geoip2._
import io.gatling.core.Predef._
import io.gatling.core.Predef.{stringToExpression => _, _}
import io.gatling.core.session.Expression

import scala.util.Random
import scala.concurrent.duration._

class MMDBServerSimulation extends Simulation {
  val ip       = sys.env.getOrElse("MMDB_SERVER_HOST", "localhost")
  val port     = sys.env.getOrElse("MMDB_SERVER_PORT", "50000").toInt
  val mcb      = managedChannelBuilder(ip, port).usePlaintext()
  val protocol = grpc(mcb).shareChannel

  def o      = Random.nextInt(224) + 30
  val feeder = Iterator.continually(Map("ip" -> s"$o.$o.$o.$o"))

  def scn(name: String) =
    scenario(name)
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
    scn("default")
      .inject(
        rampConcurrentUsers(1).to(1000).during(30.seconds),
        constantConcurrentUsers(1000).during(180.seconds),
        rampConcurrentUsers(1000).to(10).during(30.seconds),
        constantConcurrentUsers(10).during(180.seconds)
      )
      .protocols(protocol)
  )
}
