package com.sample.project.presentation.postDetail

sealed class PostDetailEvent {
    data class LoadPost(val id: Int) : PostDetailEvent()
}