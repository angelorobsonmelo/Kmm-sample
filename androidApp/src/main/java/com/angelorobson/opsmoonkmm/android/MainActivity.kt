package com.angelorobson.opsmoonkmm.android

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.asLiveData
import com.angelorobson.opsmoonkmm.Greeting
import com.angelorobson.opsmoonkmm.data.repository.remote.PostRemoteRepository
import com.angelorobson.opsmoonkmm.domain.usecases.GetPostUseCase
import com.angelorobson.opsmoonkmm.utils.RequestState
//import com.angelorobson.opsmoonkmm.viewmodel.PostsViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    // View Model
//    lateinit var viewModel: PostsViewModel
    private lateinit var tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv = findViewById(R.id.text_view)

//        initViewModel()
    }

//    private fun initViewModel() {
//        viewModel = ViewModelProviders.of(this).get(PostsViewModel::class.java)
//
//        viewModel.getPosts()
//
//        viewModel.allRepositories.asLiveData().observe(this) {
//            when (it) {
//                is RequestState.Error -> {
//
//                }
//                RequestState.Idle -> {
//
//                }
//                RequestState.Loading -> {
//
//                }
//                is RequestState.Success -> {
//                    tv.text = it.data.random().title
//                }
//            }
//        }
//    }
}
