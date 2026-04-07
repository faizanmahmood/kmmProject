package com.sample.project.presentation.postDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sample.project.presentation.postList.PostListEvent
import com.sample.project.presentation.postList.PostListUiStates

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