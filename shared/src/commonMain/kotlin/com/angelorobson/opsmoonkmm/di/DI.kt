package com.angelorobson.opsmoonkmm.di

import com.angelorobson.opsmoonkmm.initLogger
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

val httpclient = HttpClient() {
    install(Logging) {
        level = LogLevel.BODY
        logger = object : Logger {
            override fun log(message: String) {
                Napier.v(tag = "HTTP Client", message = message)
            }
        }
    }
    install(JsonFeature) {
        val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
        serializer = KotlinxSerializer(json = json)
    }
}.also {
    initLogger()
}