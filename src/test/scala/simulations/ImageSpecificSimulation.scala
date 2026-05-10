package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import shared._

class ImageSpecificSimulation extends Simulation {
  val clientId: String = System.getProperty("clientId", "A")
  val client = ClientProfiles.all.find(_.id == clientId).getOrElse(ClientProfiles.clientA)
  val holdDuration: Int = 15

  val badConfig1 = scenario(s"Bad Configuration 1 - ${client.name}")
    .feed(DisplayFeeder.feeder("sh:1.5/q:100/ext:png/dpr:10/plain"))
    .exec(
      http("bad_configuration_image_request")
        .get("#{imagePath}")
        .check(status.is(200))
    )
  val badConfig2 = scenario(s"Bad Configuration 2 - ${client.name}")
    .feed(DisplayFeeder.feeder("q:100/ext:png/dpr:2/plain"))
    .exec(
      http("bad_configuration_image_request")
        .get("#{imagePath}")
        .check(status.is(200))
    )

  setUp(
    badConfig1.inject(
        constantUsersPerSec(client.normalRps).during(holdDuration)
      )
      .protocols(ImgproxyProtocol.httpProtocol).andThen(
    badConfig2.inject(
      constantUsersPerSec(client.normalRps).during(holdDuration)
    )
    .protocols(ImgproxyProtocol.httpProtocol))
  ).assertions(
    global.responseTime.percentile(95).lt(1500),
    global.successfulRequests.percent.gte(99)
  )
}