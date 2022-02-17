package com.angelorobson.opsmoonkmm.presentation.viewmodel

import com.angelorobson.opsmoonkmm.di.KodeinInjector
import com.angelorobson.opsmoonkmm.domain.models.PostResponse
import com.angelorobson.opsmoonkmm.domain.usecases.GetPostUseCase
import com.angelorobson.opsmoonkmm.utils.network.NetworkResult
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.kodein.di.instance

class PostsViewModel : ViewModel() {

    private val useCase by KodeinInjector.instance<GetPostUseCase>()

    private val _allPostsLiveData =
        MutableLiveData<NetworkResult<List<PostResponse>>>(NetworkResult.Idle())
    val allPostsLiveData: LiveData<NetworkResult<List<PostResponse>>> get() = _allPostsLiveData

    fun getPosts() {
        try {
            viewModelScope.launch {
                useCase.getPosts()
                    .onStart {
                        _allPostsLiveData.value = NetworkResult.Loading()
                    }
                    .catch {
                        _allPostsLiveData.value = NetworkResult.Error(it.message)
                    }
                    .collect {
                        _allPostsLiveData.value = NetworkResult.Success(it)
                    }
            }
        } catch (ex: Exception) {
            _allPostsLiveData.value = NetworkResult.Error(ex.message)
        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}