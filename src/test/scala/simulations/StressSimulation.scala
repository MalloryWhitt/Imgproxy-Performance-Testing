package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import shared.ImgproxyProtocol
import shared.ImagePaths
import shared.ClientProfiles
import shared.DisplayFeeder

class StressSimulation extends Simulation {

	val clientId: String = System.getProperty("clientId", "A")
    val client = ClientProfiles.all.find(_.id == clientId).getOrElse(ClientProfiles.clientA)
    val holdDuration: Int = 240

    val stressScenario = scenario(s"Stress Test - ${client.name}")
        .feed(DisplayFeeder.feeder)
        .exec(
            http("stress_image_request")
                .get("#{imagePath}")
                .check(status.is(200))
        )

	setUp(
	    stressScenario.inject(
	    	nothingFor(5),
	    	rampUsersPerSec(0).to(client.normalRps).during(60),
	        rampUsersPerSec(client.normalRps).to(client.normalRps * 10).during(holdDuration)
	    )
	    .protocols(ImgproxyProtocol.httpProtocol)
	)
}