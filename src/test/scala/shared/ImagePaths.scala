package shared

case class ImagePaths(format: String = "plain") {
	val mobile  = s"/insecure/rs:fit:400:300/$format/https://picsum.photos/1920/1080"
	val tablet  = s"/insecure/rs:fit:800:600/$format/https://picsum.photos/1920/1080"
	val desktop = s"/insecure/rs:fit:1920:1080/$format/https://picsum.photos/1920/1080"
}