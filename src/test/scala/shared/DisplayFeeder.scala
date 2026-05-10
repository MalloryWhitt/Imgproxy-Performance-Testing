package shared

import io.gatling.core.Predef._
import shared.ImagePaths

object DisplayFeeder {
  def feeder(format: String = "plain") = {
    val paths = ImagePaths(format)
    Array(
      Map("imagePath" -> paths.mobile),
      Map("imagePath" -> paths.mobile),
      Map("imagePath" -> paths.mobile),
      Map("imagePath" -> paths.mobile),
      Map("imagePath" -> paths.mobile),
      Map("imagePath" -> paths.mobile),
      Map("imagePath" -> paths.mobile),
      Map("imagePath" -> paths.desktop),
      Map("imagePath" -> paths.desktop),
      Map("imagePath" -> paths.tablet)
    ).circular
  }
}