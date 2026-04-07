package com.sample.project

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.sample.project.core.PostDetail
import com.sample.project.core.PostList
import com.sample.project.core.rememberAppNavBackStack
import com.sample.project.presentation.postDetail.PostDetailScreen
import com.sample.project.presentation.postDetail.PostDetailViewModel
import com.sample.project.presentation.postList.PostListEvent
import com.sample.project.presentation.postList.PostListScreen
import com.sample.project.presentation.postList.PostListViewModel
import org.koin.compose.getKoin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun SampleApplication() {
    val backStack = rememberAppNavBackStack()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Posts") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        contentWindowInsets = WindowInsets.systemBars.exclude(WindowInsets.navigationBars),
    ) { innerPadding ->
        NavDisplay(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding),
            backStack = backStack,
            onBack = { backStack.removeLastOrNull() }

        ) { key ->

            when (key) {
                is PostList -> NavEntry(key) {

                    val viewModel: PostListViewModel = getKoin().get()

                    val state by viewModel.uiState.collectAsState()

                    LaunchedEffect(Unit) {
                        viewModel.events.collect { event ->
                            if (event is PostListEvent.OnPostClick){
                                backStack.add(PostDetail(id = event.post.id))
                            }
                        }
                    }

                    PostListScreen(
                        state = state
                    ) { event ->
                        viewModel.onEvent(event)
                    }
                }

                is PostDetail -> NavEntry(key) {

                    val viewModel: PostDetailViewModel = getKoin().get()

                    val state by viewModel.uiState.collectAsState()


                    PostDetailScreen(postId = key.id, state) { event ->
                        viewModel.onEvent(event)
                    }
                }

                else -> {
                    error("Unknown route: $key")
                }
            }
        }
    }


}