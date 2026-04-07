package com.sample.project.presentation.postDetail

import com.sample.project.domain.post.PostData

data class PostDetailUiStates(
    val isLoading: Boolean = false,
    val post: PostData? = null,
    val error: String? = null
)