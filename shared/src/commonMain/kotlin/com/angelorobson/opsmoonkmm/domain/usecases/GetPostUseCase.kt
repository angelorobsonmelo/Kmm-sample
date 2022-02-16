package com.angelorobson.opsmoonkmm.domain.usecases

import com.angelorobson.opsmoonkmm.domain.models.PostResponse
import kotlinx.coroutines.flow.Flow

interface GetPostUseCase {

    suspend fun getPosts(): Flow<List<PostResponse>>

}