package com.angelorobson.opsmoonkmm.domain.usecases

import com.angelorobson.opsmoonkmm.domain.models.PostResponse
import com.angelorobson.opsmoonkmm.data.repository.remote.PostRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPostUseCaseImpl(
    private val repository: PostRemoteRepository
) : GetPostUseCase {

    override suspend fun getPosts(): Flow<List<PostResponse>> {
        return flow {
            val items = repository.getPosts()
            emit(items)
        }
    }
}