package com.angelorobson.opsmoonkmm.data.datasource.local

import com.angelorobson.opsmoonkmm.domain.models.PostResponse


interface IPostLocalDataSource {

    suspend fun saveAll(posts: List<PostResponse>)

}