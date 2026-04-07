package com.sample.project.data.remote.responses.post

import kotlinx.serialization.Serializable

@Serializable
data class PostListResponse(
    val userId: Int?,
    val id: Int?,
    val title: String?,
    val body: String?
)