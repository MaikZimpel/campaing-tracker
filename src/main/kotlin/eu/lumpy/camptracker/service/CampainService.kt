package eu.lumpy.camptracker.service

import eu.lumpy.camptracker.esi.EsiClient
import io.vertx.core.Vertx
import io.vertx.ext.web.Router

fun main(args: Array<String>) {
    println("Server is starting.")
    val vertx = Vertx.vertx()
    val httpServer = vertx.createHttpServer()
    val router = Router.router(vertx)
    val httpClient = vertx.createHttpClient()
    val esiClient = EsiClient("https://esi.evetech.net/latest")


    router.route("/").handler { ctx ->
        val response = ctx.response()
        response
                .putHeader("content-type", "text/plain")
                .setChunked(true)
                .write("Hello World!")
                .end()
    }

    httpServer
            .requestHandler(router::accept)
            .listen(8081)
}