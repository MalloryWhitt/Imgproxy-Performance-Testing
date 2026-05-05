package shared

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object ImgproxyProtocol {

  val baseUrl = "https://imgproxy-st.tuxed.dev"

  val httpProtocol = http
    .baseUrl(baseUrl)
    .acceptHeader("image/webp,image/*,*/*")
    .acceptEncodingHeader("gzip, deflate, br")
    .userAgentHeader("Gatling LoadTest - ImgTestSquad/1.0")
}