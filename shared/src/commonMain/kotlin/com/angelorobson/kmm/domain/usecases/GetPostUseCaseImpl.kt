package com.angelorobson.kmm.domain.usecases

import com.angelorobson.kmm.domain.models.PostResponse
import com.angelorobson.kmm.data.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPostUseCaseImpl(
    private val repository: PostRepository
) : IGetPostUseCase {

    override suspend fun getPosts(): Flow<List<PostResponse>> {
        return flow {
            val items = repository.getPostsFromService()
            repository.deleteAllLocally()
            repository.saveAllLocally(items)
            val itemsLocally = repository.getAllFromLocalDatabase()
            emit(itemsLocally)
        }
    }
}