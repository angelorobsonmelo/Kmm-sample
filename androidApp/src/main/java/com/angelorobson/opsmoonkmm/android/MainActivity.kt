package com.angelorobson.opsmoonkmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.angelorobson.opsmoonkmm.Greeting
import android.widget.TextView
import com.angelorobson.opsmoonkmm.Hello
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private val mainScope = MainScope()
    private val greeting = Greeting()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = "Loading..."

        mainScope.launch {
            kotlin.runCatching {
                greeting.greeting()
            }.onSuccess {
                tv.text = it
            }.onFailure {
                tv.text = it.message
            }
        }
    }
}
