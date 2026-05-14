package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import shared._

class StressSimulation extends Simulation {

	val clientId: String = System.getProperty("clientId", "A")
	val client = ClientProfiles.all.find(_.id == clientId).getOrElse(ClientProfiles.clientA)
	val rampDuration: Int = 180
	val stressFactor: Int = 10

	val stressScenario = scenario(s"Stress Test - ${client.name}")
			.feed(DisplayFeeder.feeder())
			.exec(
				http("stress_image_request")
					.get("#{imagePath}")
					.check(status.is(200))
			)

	setUp(
	    stressScenario.inject(
	    	nothingFor(5),
	    	rampUsersPerSec(0).to(client.normalRps).during(60),
	        rampUsersPerSec(client.normalRps).to(client.normalRps * stressFactor).during(rampDuration)
	    )
	    .protocols(ImgproxyProtocol.httpProtocol)
	)
}