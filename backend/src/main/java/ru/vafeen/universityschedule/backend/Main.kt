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
            get("/test") {
                call.respondText { "Test successful!" }
            }
            get("/") {
                call.respondText("Hello, world!")
            }
            get("/groups") {
                call.respondText {
                    converter.convert(groups).toString()
                }
            }
            get("/{group}") {
                val groupId = call.parameters["group"]?.toIntOrNull()
                call.respondText {
                    converter.convert(lessons.filter {
                        it.groupId == groupId
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