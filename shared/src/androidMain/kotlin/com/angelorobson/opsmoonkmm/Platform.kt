package com.angelorobson.opsmoonkmm

import android.content.Context
import com.angelorobson.opsmoonkmm.db.PostDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import java.util.concurrent.TimeUnit

actual class Platform actual constructor() {
    actual val platform: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(OkHttp) {
    config(this)

    engine {
        config {
            retryOnConnectionFailure(true)
            connectTimeout(5, TimeUnit.SECONDS)
        }
    }
}

actual fun initLogger() {
    Napier.base(DebugAntilog())
}

lateinit var appContext: Context

internal actual fun cache(): PostDatabase {
    val driver = AndroidSqliteDriver(PostDatabase.Schema, appContext, "posts.db")
    return PostDatabase(driver)
}