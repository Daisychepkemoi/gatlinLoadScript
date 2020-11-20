package gatling

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class getToWebsite extends Simulation {

	val httpProtocol = http
		.baseUrl("http://computer-database.gatling.io")
		.inferHtmlResources()
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



	val scn = scenario("getToWebsite")
		.exec(http("computers")
			.get("/computers")
			.headers(headers_0))
		.pause(1)
		.exec(http("searchComputers")
			.get("/computers?f=ace")
			.headers(headers_0)
			.resources(http("request_5")
			.get("/assets/css/main.css"),
            http("request_6")
			.get("/assets/css/bootstrap.min.css")))
		.pause(6)
		.exec(http("getOneComputer")
			.get("/computers/381")
			.headers(headers_0)
			.resources(http("request_8")
			.get("/assets/css/main.css"),
            http("request_9")
			.get("/assets/css/bootstrap.min.css")))
		.pause(26)
		.exec(http("updateComputer")
			.post("/computers/381")
			.headers(headers_10)
			.formParam("name", "ACE")
			.formParam("introduced", "2001-01-01")
			.formParam("discontinued", "2010-01-01")
			.formParam("company", "28")
			.resources(http("request_11")
			.get("/assets/css/main.css"),
            http("request_12")
			.get("/assets/css/bootstrap.min.css")))
		.pause(7)
		.exec(http("createComputer")
			.post("/computers")
			.headers(headers_10)
			.formParam("name", "HPTEC")
			.formParam("introduced", "")
			.formParam("discontinued", "")
			.formParam("company", "")
			.resources(http("request_17")
			.get("/assets/css/main.css"),
            http("request_18")
			.get("/assets/css/bootstrap.min.css")))
		.pause(14)
		.exec(http("deleteComputer")
			.post("/computers/330/delete")
			.headers(headers_10)
			.resources(http("request_47")
			.get("/assets/css/main.css"),
            http("request_48")
			.get("/assets/css/bootstrap.min.css")))

	setUp(scn.inject(atOnceUsers(10))).protocols(httpProtocol)
}