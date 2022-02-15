package com.angelorobson.opsmoonkmm

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*


class Greeting {

    val httpclient = HttpClient()

    suspend fun greeting(): String {
        return "${getJson()}, ${Platform().platform}!"
    }

    private suspend fun getJson(): String {
        val response: HttpResponse = httpclient.get("https://jsonplaceholder.typicode.com/todos")
        return response.readText()
    }
}