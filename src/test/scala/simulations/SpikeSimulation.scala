package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import shared.ImgproxyProtocol
import shared.ImagePaths
import shared.ClientProfiles
import shared.DisplayFeeder

import scala.concurrent.duration._

class SpikeSimulation extends Simulation {

    val spikeMultiplier: Int = 10

    val populationBuilders = ClientProfiles.all.map { client =>
        
        val spikeScenario = scenario(s"Spike Test - ${client.name}")
            .feed(DisplayFeeder.feeder)
            .exec(
                http(s"spike_request_${client.name}")
                    .get("#{imagePath}")
                    .check(status.is(200))
            )

        spikeScenario.inject(
            nothingFor(5.seconds),
            
            rampUsersPerSec(0).to(client.normalRps).during(30.seconds),
            constantUsersPerSec(client.normalRps).during(1.minute),
            
            rampUsersPerSec(client.normalRps).to(client.normalRps * spikeMultiplier).during(10.seconds),
            constantUsersPerSec(client.normalRps * spikeMultiplier).during(20.seconds),
            
            rampUsersPerSec(client.normalRps * spikeMultiplier).to(client.normalRps).during(10.seconds),
            constantUsersPerSec(client.normalRps).during(1.minute)
        )
    }

    setUp(
        populationBuilders: _*
    )
    .protocols(ImgproxyProtocol.httpProtocol)
    .assertions(
        global.responseTime.percentile(95).lt(2500), 
        global.successfulRequests.percent.gte(80)   
    )
}