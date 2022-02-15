package com.angelorobson.opsmoonkmm

import io.ktor.client.*

expect class Platform() {
    val platform: String
}

expect fun httpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient

expect fun initLogger()