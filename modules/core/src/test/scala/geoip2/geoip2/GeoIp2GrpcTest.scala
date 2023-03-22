package geoip2.geoip2

import munit.FunSuite

class GeoIp2GrpcTest extends FunSuite {
  test("companion") {
    assertEquals(
      implicitly[scalapb.grpc.ServiceCompanion[GeoIpGrpc.GeoIp]].scalaDescriptor,
      GeoIpGrpc.GeoIp.scalaDescriptor
    )
  }
}
