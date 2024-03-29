package com.angelorobson.kmm.data.datasource.network

import com.angelorobson.kmm.domain.models.PostRequest
import com.angelorobson.kmm.domain.models.PostResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*

class PostRemoteDataSourceImpl(
    private val client: HttpClient
) : IPostNetworkDataSource {

    override suspend fun getPostsFromService(): List<PostResponse> {
        return try {
            client.get { url(HttpRoutes.POSTS) }
        } catch (ex: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${ex.response.status.description}")
            emptyList()
        } catch (ex: ClientRequestException) {
            // 4xx - responses
            println("Error: ${ex.response.status.description}")
            emptyList()
        } catch (ex: ServerResponseException) {
            // 5xx - responses
            println("Error: ${ex.response.status.description}")
            emptyList()
        } catch (ex: Exception) {
            // 4xx - responses
            println("Error: ${ex.message}")
            emptyList()
        }
    }

    override suspend fun createPost(postRequest: PostRequest): PostResponse? {
        return try {
            client.post<PostResponse>() {
                url(HttpRoutes.POSTS)
                contentType(ContentType.Application.Json)
                body = postRequest
            }
        } catch (ex: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${ex.response.status.description}")
            null
        } catch (ex: ClientRequestException) {
            // 4xx - responses
            println("Error: ${ex.response.status.description}")
            null
        } catch (ex: ServerResponseException) {
            // 5xx - responses
            println("Error: ${ex.response.status.description}")
            null
        } catch (ex: Exception) {
            // 4xx - responses
            println("Error: ${ex.message}")
            null
        }
    }
}
