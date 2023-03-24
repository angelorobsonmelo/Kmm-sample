package com.angelorobson.kmm.data.repository

import com.angelorobson.kmm.domain.models.PostRequest
import com.angelorobson.kmm.domain.models.PostResponse

interface PostRepository {

    suspend fun getPostsFromService(): List<PostResponse>

    suspend fun createPost(postRequest: PostRequest): PostResponse?

    suspend fun saveAllLocally(posts: List<PostResponse>)

    suspend fun deleteAllLocally()

    suspend fun getAllFromLocalDatabase(): List<PostResponse>

}