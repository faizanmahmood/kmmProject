package com.sample.project.data.remote.responses.comments

data class CommentListResponse(
    val id: Int?,
    val postId: Int?,
    val title: String?,
    val description: String?
)