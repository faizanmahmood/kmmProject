package com.sample.project.presentation.postList

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.sample.project.core.PostDetail
import org.koin.mp.KoinPlatform.getKoin

@Composable
fun PostListRoute(backStack: NavBackStack<NavKey>) {

    val viewModel = remember {
        getKoin().get<PostListViewModel>()
    }

    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onEvent(PostListEvent.LoadPosts)

        viewModel.events.collect { event ->
            if (event is PostListEvent.OnPostClick) {
                backStack.add(PostDetail(id = event.post.id))
            }
        }
    }

    PostListScreen(
        state = state,
        onEvent = viewModel::onEvent
    )
}