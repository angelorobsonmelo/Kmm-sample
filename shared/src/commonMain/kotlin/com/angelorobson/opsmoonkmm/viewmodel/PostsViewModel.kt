package com.angelorobson.opsmoonkmm.viewmodel

import com.angelorobson.opsmoonkmm.di.KodeinInjector
import com.angelorobson.opsmoonkmm.domain.models.PostResponse
import com.angelorobson.opsmoonkmm.domain.usecases.GetPostUseCase
import com.angelorobson.opsmoonkmm.utils.RequestState
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.kodein.di.instance

class PostsViewModel : ViewModel() {

    private val useCase by KodeinInjector.instance<GetPostUseCase>()

    private val _allRepositories =
        MutableStateFlow<RequestState<List<PostResponse>>>(RequestState.Idle)
    val allRepositories: StateFlow<RequestState<List<PostResponse>>> = _allRepositories

    fun getPosts() {
        try {
            viewModelScope.launch {
                useCase.getPosts()
                    .onStart {
                        _allRepositories.value = RequestState.Loading
                    }
                    .collect {
                        _allRepositories.value = RequestState.Success(it)
                    }
            }
        } catch (ex: Exception) {
            _allRepositories.value = RequestState.Error(error = ex)

        }

    }
}