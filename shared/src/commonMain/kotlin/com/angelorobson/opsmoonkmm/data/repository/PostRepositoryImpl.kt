package com.angelorobson.opsmoonkmm.data.repository

import com.angelorobson.opsmoonkmm.data.datasource.network.PostNetworkDataSource
import com.angelorobson.opsmoonkmm.domain.models.PostRequest
import com.angelorobson.opsmoonkmm.domain.models.PostResponse

class PostRepositoryImpl(
    private val networkDataSource: PostNetworkDataSource
) : PostRepository {

    override suspend fun getPosts(): List<PostResponse> = networkDataSource.getPosts()

    override suspend fun createPost(postRequest: PostRequest): PostResponse? =
        networkDataSource.createPost(postRequest)


}