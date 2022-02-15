package com.angelorobson.opsmoonkmm.android

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.angelorobson.opsmoonkmm.Greeting
import com.angelorobson.opsmoonkmm.data.repository.remote.PostRemoteRepository
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private val mainScope = MainScope()

    private val service = PostRemoteRepository.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = "Loading..."

        mainScope.launch {
            kotlin.runCatching {
                service.getPosts().random().title
            }.onSuccess {
                tv.text = it
            }.onFailure {
                tv.text = it.message
            }
        }
    }
}
