package com.sample.project.network


import com.sample.project.data.remote.responses.comments.CommentListResponse
import com.sample.project.data.remote.responses.post.PostListResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class ApiService (private val client: HttpClient){
    private val baseUrl = "https://jsonplaceholder.typicode.com/"

    suspend fun getPosts(): List<PostListResponse> =
        client.get("$baseUrl/posts").body()

    suspend fun getPostsById(id: Int): PostListResponse =
        client.get("$baseUrl/posts/$id").body()

    suspend fun getComments(postId: Int): List<CommentListResponse> =
        client.get("$baseUrl/comments") {
            parameter("postId", postId)
        }.body()
}