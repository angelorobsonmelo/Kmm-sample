package com.angelorobson.opsmoonkmm

import io.github.aakira.napier.Napier
import io.ktor.client.*
import kotlinx.serialization.Serializable
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json


@Serializable
data class Todo(
    val title: String
)

class Greeting {

    private val httpclient = HttpClient() {
        install(Logging) {
            level = LogLevel.BODY
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.v(tag = "HTTP Client", message = message)
                }
            }
        }
        install(JsonFeature) {
            val json = Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json = json)
        }
    }.also {
        initLogger()
    }

    @Throws(Exception::class)
    suspend fun greeting(): String {
        return "${getTodoList().random().title}, ${Platform().platform}!"
    }

    private suspend fun getTodoList(): List<Todo> {
        return httpclient.get("https://jsonplaceholder.typicode.com/todos")

    }
}