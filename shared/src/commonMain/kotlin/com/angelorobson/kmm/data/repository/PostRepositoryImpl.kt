package com.angelorobson.kmm.data.repository

import com.angelorobson.kmm.data.datasource.local.IPostLocalDataSource
import com.angelorobson.kmm.data.datasource.network.IPostNetworkDataSource
import com.angelorobson.kmm.domain.models.PostRequest
import com.angelorobson.kmm.domain.models.PostResponse

class PostRepositoryImpl(
    private val dataSourceINetwork: IPostNetworkDataSource,
    private val localDataSource: IPostLocalDataSource
) : PostRepository {

    override suspend fun getPostsFromService(): List<PostResponse> = dataSourceINetwork.getPostsFromService()

    override suspend fun createPost(postRequest: PostRequest): PostResponse? =
        dataSourceINetwork.createPost(postRequest)

    override suspend fun saveAllLocally(posts: List<PostResponse>) {
        localDataSource.saveAllLocally(posts)
    }

    override suspend fun deleteAllLocally() {
        localDataSource.deleteAllLocally()
    }

    override suspend fun getAllFromLocalDatabase(): List<PostResponse> {
        val posts = localDataSource.getAllFromLocalDatabase()
        return posts.map {
            PostResponse(
                id = it.id.toInt(),
                title = it.title,
                body = it.body
            )
        }
    }


}