package com.sample.project.presentation.postDetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun PostDetailScreen(
    postId: Int,
    state: PostDetailUiStates,
    onEvent: (PostDetailEvent) -> Unit
) {

    LaunchedEffect(Unit) {
        onEvent(PostDetailEvent.LoadPost(postId))
    }

}