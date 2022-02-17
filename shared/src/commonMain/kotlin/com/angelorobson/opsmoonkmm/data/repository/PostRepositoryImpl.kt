package com.angelorobson.opsmoonkmm.data.repository

import com.angelorobson.opsmoonkmm.data.datasource.local.IPostLocalDataSource
import com.angelorobson.opsmoonkmm.data.datasource.network.IPostNetworkDataSource
import com.angelorobson.opsmoonkmm.domain.models.PostRequest
import com.angelorobson.opsmoonkmm.domain.models.PostResponse

class PostRepositoryImpl(
    private val dataSourceINetwork: IPostNetworkDataSource,
    private val localDataSource: IPostLocalDataSource
) : PostRepository {

    override suspend fun getPosts(): List<PostResponse> = dataSourceINetwork.getPosts()

    override suspend fun createPost(postRequest: PostRequest): PostResponse? =
        dataSourceINetwork.createPost(postRequest)

    override suspend fun saveAll(posts: List<PostResponse>) {
        localDataSource.saveAll(posts)
    }


}