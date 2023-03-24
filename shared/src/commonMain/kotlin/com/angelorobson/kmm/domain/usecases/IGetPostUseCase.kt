package com.angelorobson.kmm.domain.usecases

import com.angelorobson.kmm.domain.models.PostResponse
import kotlinx.coroutines.flow.Flow

interface IGetPostUseCase {

    suspend fun getPosts(): Flow<List<PostResponse>>

}