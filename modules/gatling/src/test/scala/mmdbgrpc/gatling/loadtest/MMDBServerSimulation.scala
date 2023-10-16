package mmdbgrpc.gatling.loadtest

import com.github.phisgr.gatling.grpc.Predef._
import com.github.phisgr.gatling.pb._
import geoip2.geoip2.Message.Locale
import geoip2.geoip2._
import io.gatling.core.Predef._
import io.gatling.core.Predef.{stringToExpression => _, _}
import io.gatling.core.session.Expression

import scala.concurrent.duration._
import scala.util.Random

class MMDBServerSimulation extends Simulation {
  val ip       = sys.env.getOrElse("MMDB_SERVER_HOST", "localhost")
  val port     = sys.env.getOrElse("MMDB_SERVER_PORT", "50000").toInt
  val mcb      = managedChannelBuilder(ip, port).usePlaintext()
  def protocol = grpc(mcb).shareChannel

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
    scn("demo1")
      .inject(
        nothingFor(4.seconds),
        atOnceUsers(10),
        rampUsers(10).during(5.seconds),
        constantUsersPerSec(20).during(15.seconds),
        rampUsersPerSec(10).to(20).during(10.seconds),
        stressPeakUsers(1000).during(20.seconds)
      )
      .protocols(protocol),
    scn("demo2")
      .inject(
        constantUsersPerSec(20).during(15.seconds).randomized,
        rampUsersPerSec(10).to(20).during(10.seconds).randomized
      )
      .protocols(protocol)
  )
}
