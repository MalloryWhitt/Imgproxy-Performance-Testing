package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import shared.ImgproxyProtocol
import shared.ImagePaths
import shared.ClientProfiles
import shared.DisplayFeeder

class StandardLoadSimulation extends Simulation {

	val clientId: String = System.getProperty("clientId", "A")
	val client = ClientProfiles.all.find(_.id == clientId).getOrElse(ClientProfiles.clientA)
	val holdDuration: Int = 180

	val standardScenario = scenario(s"Standard Load - ${client.name}")
		.feed(DisplayFeeder.feeder)
		.exec(
			http("standard_image_request")
				.get("#{imagePath}")
				.check(status.is(200))
		)
		
	setUp(
		standardScenario.inject(
			nothingFor(5),
			rampUsersPerSec(0).to(client.normalRps).during(60),
			constantUsersPerSec(client.normalRps).during(holdDuration)
		)
		.protocols(ImgproxyProtocol.httpProtocol)
		).assertions(
			global.responseTime.percentile(95).lt(1500),
			global.successfulRequests.percent.gte(99)
		)
}