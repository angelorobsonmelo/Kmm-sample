package com.angelorobson.opsmoonkmm.data.repository.remote

import com.angelorobson.opsmoonkmm.domain.models.PostRequest
import com.angelorobson.opsmoonkmm.domain.models.PostResponse


interface PostRemoteRepository {

    suspend fun getPosts(): List<PostResponse>

    suspend fun createPost(postRequest: PostRequest): PostResponse?

}