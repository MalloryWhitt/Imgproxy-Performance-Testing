package shared

case class ClientProfile(id: String, name: String, normalRps: Double)

object ClientProfiles {
	val clientA = ClientProfile("A", "HouseOfFraser", 1)
	val clientB = ClientProfile("B", "Joules", 2)
	val clientC = ClientProfile("C", "Boden", 3)
	val clientD = ClientProfile("D", "Zalando", 5)
	val clientE = ClientProfile("E", "ASOS", 15)
  
	val all = List(clientA, clientB, clientC, clientD, clientE)
}