package com.angelorobson.opsmoonkmm.data.datasource.local

import com.angelorobson.opsmoonkmm.db.Post
import com.angelorobson.opsmoonkmm.domain.models.PostResponse


interface IPostLocalDataSource {

    suspend fun saveAllLocally(posts: List<PostResponse>)
    suspend fun deleteAllLocally()
    suspend fun getAllFromLocalDatabase(): List<Post>

}