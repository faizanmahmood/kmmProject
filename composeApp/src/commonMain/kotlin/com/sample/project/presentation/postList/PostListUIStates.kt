package com.sample.project.presentation.postList

import com.sample.project.domain.post.PostData

data class PostListUiStates(
    val isLoading: Boolean = false,
    val posts: List<PostData> = emptyList(),
    val error: String? = null
)