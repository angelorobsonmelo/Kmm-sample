package com.angelorobson.opsmoonkmm.data.datasource.network

import com.angelorobson.opsmoonkmm.domain.models.PostRequest
import com.angelorobson.opsmoonkmm.domain.models.PostResponse


interface IPostNetworkDataSource {

    suspend fun getPostsFromService(): List<PostResponse>

    suspend fun createPost(postRequest: PostRequest): PostResponse?

}