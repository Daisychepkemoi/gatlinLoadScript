package gatling

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class getToWebsite1 extends Simulation {

	val httpProtocol = http
		.baseUrl("http://computer-database.gatling.io")
		.inferHtmlResources(BlackList(), WhiteList("http://computer-database.gatling.io/.*"))
		.acceptHeader("text/css,*/*;q=0.1")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.5")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:81.0) Gecko/20100101 Firefox/81.0")

	val headers_0 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_3 = Map("Accept" -> "image/webp,*/*")

	val headers_10 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Origin" -> "http://computer-database.gatling.io",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_25 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
		"Cache-Control" -> "max-age=0",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_26 = Map("Cache-Control" -> "max-age=0")

	  val feeder = csv("data2.csv").random // 1, 2

//   val search = scenario("Scenario Name").exec(http("Home")
//     .get("/"))
//     .pause(1)
//     .feed(feeder) // 3
//     .exec(http("Search")
//       .get("/computers?f=${name}") // 4
//       .check(css("a:contains('${name}')", "href").saveAs("company"))) // 5
//     .pause(1)
//     .exec(http("Select")
//       .get("/${company}")) // 6
//     .pause(1)
//     .exec { session =>
//   println(session)
//   session
// }

    // setUp(search.inject(constantConcurrentUsers(1) during(2 seconds))).protocols(httpProtocol)
// }

	// val credentials = csv("data2.csv").random

// val scn = scenario("Scenario Name").exec(Browse.browse, Edit.edit)
	val scn = scenario("getToWebsite")
		.exec(http("computers")
			.get("/computers")
			.headers(headers_0))
		.pause(1)
		// .exec(http("searchComputers")
		// 	.get("/computers?f=ace")
		// 	.headers(headers_0))
			
		// .pause(6)
		// .exec(http("getOneComputer")
		// 	.get("/computers/381")
		// 	.headers(headers_0))
			
		// .pause(26)
		.feed(feeder)
		.exec(http("updateComputer")
			.post("/computers/381")
			.headers(headers_10)
			.formParam("name", "${name}")
			.formParam("introduced", "2001-01-01")
			.formParam("discontinued", "2010-01-01")
			.formParam("company", "28"))
			
		// .pause(7)
		// .exec(http("createComputer")
		// 	.post("/computers")
		// 	.headers(headers_10)
		// 	.formParam("name", "HPTEC")
		// 	.formParam("introduced", "")
		// 	.formParam("discontinued", "")
		// 	.formParam("company", ""))
			
		// .pause(14)
		// .exec(http("deleteComputer")
		// 	.post("/computers/330/delete")
		// 	.headers(headers_10))
			
		setUp(scn.inject(constantConcurrentUsers(1) during(2 seconds))).protocols(httpProtocol)

}
// object Browse {

//   val browse  = exec(http("computers")
// 			.get("/computers"))
// 			// .headers(headers_0))
// 		.pause(1)
// 		.exec(http("searchComputers")
// 			.get("/computers?f=ace")
// 			.check(substring("Computer database").exists))
// 			// .headers(headers_0))
			
// 		.pause(6)
// 		.exec(http("getOneComputer")
// 			.get("/computers/381"))
// 			// .headers(headers_0))
// }

// object Edit {

//   val edit = exec(http("updateComputer")
// 			.post("/computers/381")
// 			// .headers(headers_10)
// 			.formParam("name", "ACE")
// 			.formParam("introduced", "2001-01-01")
// 			.formParam("discontinued", "2010-01-01")
// 			.formParam("company", "28"))
			
// 		.pause(7)
// 		.exec(http("createComputer")
// 			.post("/computers")
// 			// .headers(headers_10)
// 			.formParam("name", "${searchCriterion}")
// 			.formParam("introduced", "")
// 			.formParam("discontinued", "")
// 			.formParam("company", "acc"))
			
// 		.pause(14)
// 		.exec(http("deleteComputer")
// 			.post("/computers/330/delete"))
// 			// .headers(headers_10))
// }