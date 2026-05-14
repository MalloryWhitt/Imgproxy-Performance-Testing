# ImgTestSquad - Imgproxy Performance Testing

THWS Software Testing Project: Gatling load and performance tests for imgproxy.

## Team
| Name | Simulations |
|---|---|
| Adrian | ImageSpecificSimulation |
| Mallory | BaselineSimulation, StandardLoadSimulation, StressSimulation |
| Luciano | SoakSimulation, SpikeSimulation |

## Testing Target (hosted by Adrian)
https://imgproxy-st.tuxed.dev

## Prerequisites
- Java 21+
- Maven 3.9+

## The tests are ran as simulations using clients with different levels of requests per second specified in ClientProfiles.scala
Available clientIds: A (HouseOfFraser), B (Joules), C (Boden), D (Zalando), E (ASOS)

## Run BaselineSimulation without a specific client across the three display types (mobile, tablet, and desktop):
mvn gatling:test -Dgatling.simulationClass=simulations.BaselineSimulation

## Run StandardLoad, Stress, and ImageSpecific simulations with a specific client:
mvn gatling:test -Dgatling.simulationClass=simulations.StandardLoadSimulation -DclientId=A

mvn gatling:test -Dgatling.simulationClass=simulations.StressSimulation -DclientId=A

mvn gatling:test -Dgatling.simulationClass=simulations.ImageSpecificSimulation -DclientId=A

## Run Soak and Spike simulations across all clients:
mvn gatling:test -Dgatling.simulationClass=simulations.SpikeSimulation -DclientId=A

mvn gatling:test -Dgatling.simulationClass=simulations.SoakSimulation -DclientId=A

## Run all simulations
mvn gatling:test

## Results
Reports are generated automatically in target/gatling/. Open index.html in a browser to view each comprehensive report.

A report is generated for each simulation type every time the test is ran.

## PMO - Formatter
Import PMO - Formatter.xml in Eclipse as per the project specifications. Ensure to activate in save actions.
