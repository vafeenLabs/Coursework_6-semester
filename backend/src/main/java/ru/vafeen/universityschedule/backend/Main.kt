package ru.vafeen.universityschedule.backend

import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import ru.vafeen.universityschdeule.backenddto.converters.JsonStringTemplateConverter

val converter = JsonStringTemplateConverter()
fun main() {
    embeddedServer(Netty, host = "0.0.0.0", port = 8080) {
        routing {
            get("/") {
                call.respondText("Hello, world!")
            }
            get("/{group}") {
                val group = call.parameters["group"]
                call.respondText {
                    converter.convert(lessons.filter {
                        it.group == group
                    }).toString()
                }
            }
            get("/all") {
                call.respondText {
                    converter.convert(lessons).toString()
                }
            }
        }
    }.start(wait = true)
}