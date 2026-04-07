package com.sample.project.presentation.postList

import com.sample.project.domain.post.PostData

sealed class PostListEvent {
    data object LoadPosts : PostListEvent()
    data class OnPostClick(val post: PostData) : PostListEvent()
}