package eu.lumpy.camptracker.service

import eu.lumpy.camptracker.esi.EsiClient
import io.vertx.core.Vertx
import io.vertx.core.http.HttpMethod
import io.vertx.core.json.JsonObject
import io.vertx.ext.mongo.MongoClient
import io.vertx.ext.web.Router
import io.vertx.kotlin.core.json.json
import io.vertx.kotlin.core.json.obj


class CampainService {}

fun main(args: Array<String>) {

    println("Server is starting.")
    val vertx = Vertx.vertx()
    val httpServer = vertx.createHttpServer()
    val router = Router.router(vertx)
    val httpClient = vertx.createHttpClient()
    val esiClient = EsiClient("https://esi.evetech.net/latest")
    val mongoConfig = JsonObject(CampainService::class.java.classLoader.getResource("mongodb.config").readText())
    val mongoClient =  MongoClient.createShared(vertx, mongoConfig)

    println("Loading Alliances from ESI.")
    esiClient.allianceInfo().map { allianceId ->
       mongoClient.count("alliances", json { obj("id" to allianceId)}) { result ->
           if (result.result() == 0L) {
               val alliance = esiClient.allianceInfo(allianceId)
               val document = json {
                   obj (
                           "id" to alliance.id,
                           "name" to alliance.name,
                           "ticker" to alliance.ticker
                   )
               }
               mongoClient.insert("alliances", document) {res ->
                   if (res.succeeded()) {
                       val id = res.result()
                       println("Saved Alliance with id $id")
                   } else {
                       res.cause().printStackTrace()
                   }
               }
           }
       }
    }
    println("Done loading alliances.")

    router.route("/").handler { ctx ->


        val response = ctx.response()
        response
                .putHeader("content-type", "text/plain")
                .setChunked(true)
                .write("Hello World!")
                .end()
    }

    router.route(HttpMethod.GET, "/:allianceOne/:allianceTwo/:start/:end").handler { ctx ->

        val allianceOne = ctx.request().getParam("allianceOne")
        val allianceTwo = ctx.request().getParam("allianceTwo")
        val start = ctx.request().getParam("start")
        val end = ctx.request().getParam("end")
        println("Requested report: /${allianceOne}/${allianceTwo}/${start}/${end}")

    }

    router.route(HttpMethod.GET, "/alliances").handler {ctx ->
        val name = ctx.request().params()["name"]

        val query = json { obj("name" to obj("\$regex" to ".*$name.*")) }
        ctx.response().putHeader("content-type", "application/json")
        mongoClient.find("alliances", query) { result ->
            if (result.succeeded()) {
                var res = ""
                for (json in result.result()) {
                    res += json.toString()
                }
                ctx.response().setChunked(true).end(res)

            } else {
                result.cause().printStackTrace()
            }
        }
    }

    httpServer
            .requestHandler(router::accept)
            .listen(8081)
}
