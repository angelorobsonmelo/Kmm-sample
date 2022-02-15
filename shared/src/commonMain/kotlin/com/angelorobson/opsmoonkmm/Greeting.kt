package com.angelorobson.opsmoonkmm

import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*


class Greeting {

    private val httpclient = HttpClient() {
        install(Logging) {
            level = LogLevel.HEADERS
            logger = object : Logger {
                override fun log(message: String) {
                    Napier.v(tag = "HTTP Client", message = message)
                }

            }
        }
    }.also {
        initLogger()
    }

    suspend fun greeting(): String {
        return "${getJson()}, ${Platform().platform}!"
    }

    private suspend fun getJson(): String {
        val response: HttpResponse = httpclient.get("https://jsonplaceholder.typicode.com/todos")
        return response.readText()
    }
}