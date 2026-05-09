package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import shared.ImgproxyProtocol
import shared.ImagePaths

class BaselineSimulation extends Simulation {

	val userCount: Int = 1

	val baselineScenario = scenario("Baseline - Single User Reference")
		.exec(
			http("baseline_mobile")
        		.get(ImagePaths.mobile)
        		.check(status.is(200))
        )
	    .exec(
	    	http("baseline_tablet")
	        	.get(ImagePaths.tablet)
	        	.check(status.is(200))
	    )
	    .exec(
	    	http("baseline_desktop")
	        	.get(ImagePaths.desktop)
	        	.check(status.is(200))
	    )

	setUp(
    	baselineScenario.inject(
    		atOnceUsers(userCount)
    	)
	    .protocols(ImgproxyProtocol.httpProtocol)
		).assertions(
		    global.responseTime.max.lt(1000),
		    global.successfulRequests.percent.gte(100)
	   	)
}