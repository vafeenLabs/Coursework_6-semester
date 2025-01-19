package ru.vafeen.universityschedule.backend

import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import ru.vafeen.universityschedule.backenddto.LessonDTO
import ru.vafeen.universityschedule.backenddto.converters.JsonStringTemplateConverter

val converter = JsonStringTemplateConverter()
val lessons =
    sequenceOf<LessonDTO>().plus(lessons3).plus(lessons5).plus(lessons6).plus(lessons7)
        .plus(lessons8)
        .plus(lessons61).plus(lessons62).plus(lessons71).plus(lessons72).toList()

fun main() {
    embeddedServer(Netty, host = "0.0.0.0", port = 8080) {
        routing {
            get("/test") {
                call.respondText { "Test successful!" }
            }
            get("/") {
                call.respondText("Hello, world!")
            }
            get("/teachers/{teacher}") {
                val name = call.parameters["teacher"]
                call.respondText {
                    converter.convert(lessons.filter {
                        it.teacher == name
                    }).toString()
                }
            }
            get("/teachers") {
                call.respondText {
                    converter.convert(lessons.map {
                        it.teacher
                    }.distinct()).toString()
                }
            }
            get("/groups") {
                call.respondText {
                    converter.convert(groups).toString()
                }
            }
            // поменялся энд поинт
            get("/group/{group}") {
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