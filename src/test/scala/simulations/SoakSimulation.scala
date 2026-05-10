package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import shared.ImgproxyProtocol
import shared.ImagePaths
import shared.ClientProfiles
import shared.DisplayFeeder

import scala.concurrent.duration._

class SoakSimulation extends Simulation {

    val soakDuration: Int = 300 
    val populationBuilders = ClientProfiles.all.map { client =>
        
        val soakScenario = scenario(s"Soak Test - ${client.name}")
            .feed(DisplayFeeder.feeder)
            .exec(
                http(s"soak_request_${client.name}")
                    .get("#{imagePath}")
                    .check(status.is(200))
            )

        soakScenario.inject(
            nothingFor(5.seconds),
            rampUsersPerSec(0).to(client.normalRps).during(60.seconds),
            constantUsersPerSec(client.normalRps).during(soakDuration.seconds)
        )
    }

    setUp(
        populationBuilders: _*
    )
    .protocols(ImgproxyProtocol.httpProtocol)
    .assertions(
        global.responseTime.percentile(95).lt(1500),
        global.successfulRequests.percent.gte(99)
    )
}