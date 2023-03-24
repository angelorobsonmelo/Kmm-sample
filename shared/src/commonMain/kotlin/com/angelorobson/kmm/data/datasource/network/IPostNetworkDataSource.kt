package com.angelorobson.kmm.data.datasource.network

import com.angelorobson.kmm.domain.models.PostRequest
import com.angelorobson.kmm.domain.models.PostResponse


interface IPostNetworkDataSource {

    suspend fun getPostsFromService(): List<PostResponse>

    suspend fun createPost(postRequest: PostRequest): PostResponse?

}