# ImgTestSquad - Imgproxy Performance Testing

THWS Software Testing Project: Gatling load and performance tests for imgproxy.

## Team
| Name | Simulations |
| Adrian | BaselineSimulation, StandardLoadSimulation |
| Mallory | StressSimulation, SpikeSimulation |
| Giuliano | SoakSimulation, ImageSpecificSimulation |

## Testing Target (hosted by Adrian)
https://imgproxy-st.tuxed.dev

## Prerequisites
- Java 21+
- Maven 3.9+

## Run a specific simulation (example: baseline)
mvn gatling:test -Dgatling.simulationClass=simulations.BaselineSimulation

## Run all simulations
mvn gatling:test

## Results
Reports are generated automatically in target/gatling/, open index.html in a browser to view each comprehensive report.
A report is generated for each simulation type every time the test is ran.

## PMO - Formatter
Import PMO - Formatter.xml in Eclipse as per the project specifications.