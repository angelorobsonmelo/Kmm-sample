package com.angelorobson.opsmoonkmm.data.datasource.local

import com.angelorobson.opsmoonkmm.db.AppDatabseQueries
import com.angelorobson.opsmoonkmm.db.Post
import com.angelorobson.opsmoonkmm.domain.models.PostResponse

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