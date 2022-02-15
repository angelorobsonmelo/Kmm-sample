package com.angelorobson.opsmoonkmm

import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import kotlinx.serialization.json.Json


class Greeting {

    @Throws(Exception::class)
    suspend fun greeting(): String {
        return "${Platform().platform}!"
    }

}