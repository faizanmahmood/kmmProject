package com.sample.project.presentation.postDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun PostDetailScreen(
    postId: Int,
    state: PostDetailUiStates,
    onEvent: (PostDetailEvent) -> Unit
) {

    LaunchedEffect(Unit) {
        onEvent(PostDetailEvent.LoadPost(postId))
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Detail Screen $postId")
    }

}