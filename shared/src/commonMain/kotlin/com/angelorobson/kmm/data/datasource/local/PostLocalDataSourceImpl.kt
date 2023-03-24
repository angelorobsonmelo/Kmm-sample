package com.angelorobson.kmm.data.datasource.local

import com.angelorobson.kmm.db.AppDatabseQueries
import com.angelorobson.kmm.db.Post
import com.angelorobson.kmm.domain.models.PostResponse

class PostLocalDataSourceImpl(
    private val queries: AppDatabseQueries
) : IPostLocalDataSource {

    override suspend fun saveAllLocally(posts: List<PostResponse>) {
        posts.forEach {
            queries.insertItem(
                it.id.toLong(),
                it.title,
                it.body
            )
        }
    }

    override suspend fun deleteAllLocally() = queries.deleteAll()


    override suspend fun getAllFromLocalDatabase(): List<Post> = queries.selectAll().executeAsList()



}