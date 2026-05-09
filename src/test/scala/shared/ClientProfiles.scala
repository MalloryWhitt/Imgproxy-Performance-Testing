package shared

case class ClientProfile(id: String, name: String, normalRps: Double)

object ClientProfiles {
	val clientA = ClientProfile("A", "HouseOfFraser", 0.00046)
	val clientB = ClientProfile("B", "Joules", 0.154)
	val clientC = ClientProfile("C", "Boden", 1.32)
	val clientD = ClientProfile("D", "Zalando", 2.86)
	val clientE = ClientProfile("E", "ASOS", 11.38)
  
	val all = List(clientA, clientB, clientC, clientD, clientE)
}