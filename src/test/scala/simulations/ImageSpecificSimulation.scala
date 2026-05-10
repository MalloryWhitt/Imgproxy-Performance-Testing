package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import shared._

class ImageSpecificSimulation extends Simulation {
	val clientId: String = System.getProperty("clientId", "A")
	val client = ClientProfiles.all.find(_.id == clientId).getOrElse(ClientProfiles.clientA)
	val holdDuration: Int = 180

	val config1 = scenario(s"JPG Only - ${client.name}")
    	.feed(DisplayFeeder.feeder("ext:jpg/plain"))
    	.exec(
      	http("jpg_only")
        	.get("#{imagePath}")
        	.check(status.is(200))
    )
    val config2 = scenario(s"PNG Only - ${client.name}")
    	.feed(DisplayFeeder.feeder("ext:png/plain"))
    	.exec(
      	http("png_only")
        	.get("#{imagePath}")
        	.check(status.is(200))
    )
    val config3 = scenario(s"JPG High Quality - ${client.name}")
    	.feed(DisplayFeeder.feeder("q:100/ext:jpg/dpr:2/plain"))
    	.exec(
      	http("jpg_dpr2")
        	.get("#{imagePath}")
        	.check(status.is(200))
    )
    val config4 = scenario(s"PNG High Quality - ${client.name}")
    	.feed(DisplayFeeder.feeder("q:100/ext:png/dpr:2/plain"))
    	.exec(
      	http("png_dpr2")
        	.get("#{imagePath}")
        	.check(status.is(200))
    )
    val config5 = scenario(s"JPG Maximum Processing - ${client.name}")
    	.feed(DisplayFeeder.feeder("sh:1/q:100/ext:jpg/dpr:5/plain"))
    	.exec(
      	http("sharpen_jpg_dpr5")
        	.get("#{imagePath}")
        	.check(status.is(200))
    )
    val config6 = scenario(s"PNG Maximum Processing - ${client.name}")
    	.feed(DisplayFeeder.feeder("sh:1/q:100/ext:png/dpr:5/plain"))
    	.exec(
      	http("sharpen_png_dpr5")
        	.get("#{imagePath}")
        	.check(status.is(200))
    )

	setUp(
    	config1.inject(
			constantUsersPerSec(client.normalRps).during(holdDuration)
      	)
      	.protocols(ImgproxyProtocol.httpProtocol).andThen(
    	config2.inject(
        	constantUsersPerSec(client.normalRps).during(holdDuration)
    	)
    	.protocols(ImgproxyProtocol.httpProtocol)).andThen(
    	config3.inject(
        	constantUsersPerSec(client.normalRps).during(holdDuration)
    	)
    	.protocols(ImgproxyProtocol.httpProtocol)).andThen(
		config4.inject(
        	constantUsersPerSec(client.normalRps).during(holdDuration)
    	)
    	.protocols(ImgproxyProtocol.httpProtocol)).andThen(
    	config5.inject(
        	constantUsersPerSec(client.normalRps).during(holdDuration)
    	)
    	.protocols(ImgproxyProtocol.httpProtocol)).andThen(
		config6.inject(
        	constantUsersPerSec(client.normalRps).during(holdDuration)
    	)
    	.protocols(ImgproxyProtocol.httpProtocol))
  	).assertions(
    	global.responseTime.percentile(95).lt(1500),
    	global.successfulRequests.percent.gte(99)
  	)
}