package com.angelorobson.opsmoonkmm.android

//import com.angelorobson.opsmoonkmm.viewmodel.PostsViewModel
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.angelorobson.opsmoonkmm.utils.network.NetworkResult
import com.angelorobson.opsmoonkmm.viewmodel.PostsViewModel


class MainActivity : AppCompatActivity() {

    // View Model
    lateinit var viewModel: PostsViewModel
    private lateinit var tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv = findViewById(R.id.text_view)

        initViewModel()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(PostsViewModel::class.java)
        viewModel.getPosts()

        viewModel.allPostsLiveData.addObserver {
            when (it) {
                is NetworkResult.Loading -> {

                }

                is NetworkResult.Error -> {

                }

                is NetworkResult.Success -> {
                    tv.text = it.data?.random()?.title
                }
                is NetworkResult.Idle -> {

                }
            }
        }

    }
}
