# ImgTestSquad - Imgproxy Performance Testing

THWS Software Testing Project: Gatling load and performance tests for imgproxy.

## Team
| Name    | Simulations                                |
|---------|--------------------------------------------|
| Adrian  | BaselineSimulation, StandardLoadSimulation |
| Mallory | StressSimulation                           |
| Luciano | SoakSimulation, SpikeSimulation            |

## Testing Target (hosted by Adrian)
https://imgproxy-st.tuxed.dev

Can also be run locally with the following command: `docker run -p 8080:8080 -it ghcr.io/imgproxy/imgproxy:latest`.

Then the variable `baseUrl` in `src/test/scala/shared/ImgproxyProtocol.scala` needs to be changed to `http://localhost:8080

## Prerequisites
- Java 21+
- Maven 3.9+

## Run a specific simulation (example: baseline)
`mvn gatling:test -Dgatling.simulationClass=simulations.BaselineSimulation`

## Run all simulations
`mvn gatling:test`

## Results
Reports are generated automatically in `target/gatling/`, open index.html in a browser to view each comprehensive report.

A report is generated for each simulation type every time the test is ran.

## PMO - Formatter
Import PMO - Formatter.xml in Eclipse as per the project specifications. Ensure to activate in save actions.
