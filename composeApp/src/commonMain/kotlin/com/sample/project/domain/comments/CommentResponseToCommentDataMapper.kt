package com.sample.project.domain.comments

import com.sample.project.data.remote.responses.comments.CommentListResponse


fun CommentListResponse.toPostData(): CommentData {
    return CommentData(
        id = this.id ?: 0,
        title = this.title ?: "",
        description = this.description ?: "",
        postId = this.postId ?: 0
    )
}