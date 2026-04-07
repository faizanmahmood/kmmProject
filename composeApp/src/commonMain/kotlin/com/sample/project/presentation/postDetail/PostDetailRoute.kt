package com.sample.project.presentation.postDetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import org.koin.mp.KoinPlatform.getKoin

@Composable
fun PostDetailRoute(postId: Int) {

    val viewModel = remember {
        getKoin().get<PostDetailViewModel>()
    }

    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(postId) {
        viewModel.onEvent(PostDetailEvent.LoadPost(postId))
    }

    PostDetailScreen(
        postId = postId,
        state = state,
        onEvent = viewModel::onEvent
    )
}