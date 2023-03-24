package com.angelorobson.kmm.android

import android.app.Application
import com.angelorobson.kmm.appContext

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        appContext = this
    }
}