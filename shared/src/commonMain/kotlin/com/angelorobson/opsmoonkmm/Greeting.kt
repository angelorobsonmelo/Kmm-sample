package com.angelorobson.opsmoonkmm

import io.ktor.client.*


class Greeting {

    val httpclient = HttpClient()

    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}