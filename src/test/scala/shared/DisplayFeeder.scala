package shared

import io.gatling.core.Predef._

object DisplayFeeder {

	val feeder = Array(
        Map("imagePath" -> ImagePaths.mobile),
        Map("imagePath" -> ImagePaths.mobile),
        Map("imagePath" -> ImagePaths.mobile),
        Map("imagePath" -> ImagePaths.mobile),
        Map("imagePath" -> ImagePaths.mobile),
        Map("imagePath" -> ImagePaths.mobile),
        Map("imagePath" -> ImagePaths.mobile),
        Map("imagePath" -> ImagePaths.desktop),
        Map("imagePath" -> ImagePaths.desktop),
        Map("imagePath" -> ImagePaths.tablet)
    ).circular
}