package com.sample.project.domain.post

import com.sample.project.data.remote.responses.post.PostListResponse


fun PostListResponse.toPostData(): PostData {
    return PostData(
        id = this.id ?: 0,
        title = this.title ?: "",
        description = this.body ?: ""
    )
}