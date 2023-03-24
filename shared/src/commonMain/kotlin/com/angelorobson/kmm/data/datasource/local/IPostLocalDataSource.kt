package com.angelorobson.kmm.data.datasource.local

import com.angelorobson.kmm.db.Post
import com.angelorobson.kmm.domain.models.PostResponse


interface IPostLocalDataSource {

    suspend fun saveAllLocally(posts: List<PostResponse>)
    suspend fun deleteAllLocally()
    suspend fun getAllFromLocalDatabase(): List<Post>

}