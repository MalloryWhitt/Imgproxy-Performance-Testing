package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import shared.ImgproxyProtocol
import shared.ImagePaths

class BaselineSimulation extends Simulation {

  val baselineScenario = scenario("Baseline - Single User Reference")
    .exec(
      http("baseline_small_image")
        .get(ImagePaths.smallImage)
        .check(status.is(200))
    )

  setUp(
    baselineScenario.inject(
      atOnceUsers(1)
    )
  ).protocols(ImgproxyProtocol.httpProtocol)
   .assertions(
     global.responseTime.percentile(95).lt(3000),
     global.successfulRequests.percent.gte(99)
   )
}