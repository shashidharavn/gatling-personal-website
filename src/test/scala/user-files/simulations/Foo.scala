package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import assertions._

class MySimulation extends Simulation {
  val scn = scenario("Visit my blog")
    .exec(http("My blog")
      .get("https://tom-fitzhenry.me.uk/blog/"))

  setUp(scn.inject(atOnce(10 users)))
    .assertions(global.responseTime.percentile1.lessThan(750))
}