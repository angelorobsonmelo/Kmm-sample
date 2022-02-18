package com.angelorobson.opsmoonkmm.android

import android.app.Application
import com.angelorobson.opsmoonkmm.appContext

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        appContext = this
    }
}