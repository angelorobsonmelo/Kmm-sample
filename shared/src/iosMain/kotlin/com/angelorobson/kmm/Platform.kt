package com.angelorobson.kmm

import com.angelorobson.kmm.db.PostDatabase
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.engine.ios.*
import platform.UIKit.UIDevice
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver


actual class Platform actual constructor() {
    actual val platform: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(Ios) {
    config(this)

    engine {
        configureRequest {
            setAllowsCellularAccess(true)
        }
    }
}

actual fun initLogger() {
    Napier.base(DebugAntilog())
}

internal actual fun cache(): PostDatabase {
    val driver = NativeSqliteDriver(PostDatabase.Schema, "posts.db")
    return PostDatabase(driver)
}
