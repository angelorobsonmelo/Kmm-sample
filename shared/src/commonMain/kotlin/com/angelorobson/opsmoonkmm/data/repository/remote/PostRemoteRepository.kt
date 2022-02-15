package com.angelorobson.opsmoonkmm.data.repository.remote

import com.angelorobson.opsmoonkmm.data.dto.PostRequest
import com.angelorobson.opsmoonkmm.data.dto.PostResponse
import com.angelorobson.opsmoonkmm.di.httpclient


interface PostRemoteRepository {

    suspend fun getPosts(): List<PostResponse>

    suspend fun createPost(postRequest: PostRequest): PostResponse?


    companion object {
        fun create(): PostRemoteRepository {
            return PostRemoteRepositoryImpl(
                client = httpclient
            )
        }
    }

}