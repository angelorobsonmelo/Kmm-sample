package com.angelorobson.kmm
import com.angelorobson.kmm.db.PostDatabase

import io.ktor.client.*

expect class Platform() {
    val platform: String
}

expect fun httpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient

expect fun initLogger()


internal expect fun cache(): PostDatabase