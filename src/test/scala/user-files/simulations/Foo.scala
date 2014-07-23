package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import assertions._
import scala.concurrent.duration._

class MySimulation extends Simulation {
  val myHttpProto = http.baseURL("https://tom-fitzhenry.me.uk/")

  val myScn = scenario("Visit my blog")
    .exec(http("Homepage")
      .get("/blog/"))
    .pause(1,4)
    .exec(
      http("Blog post")
        .get("/blog/2012/11/personal-two-factor-authentication.html"))

  setUp(myScn.inject(ramp(20 users) over (10 seconds)))
    .protocols(myHttpProto)
    .assertions(
      global.successfulRequests.percent.is(100),
      global.responseTime.percentile1.lessThan(750))
}