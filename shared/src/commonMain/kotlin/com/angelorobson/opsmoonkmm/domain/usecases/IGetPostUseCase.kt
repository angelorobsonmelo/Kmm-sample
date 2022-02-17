package com.angelorobson.opsmoonkmm.domain.usecases

import com.angelorobson.opsmoonkmm.domain.models.PostResponse
import kotlinx.coroutines.flow.Flow

interface IGetPostUseCase {

    suspend fun getPosts(): Flow<List<PostResponse>>

}