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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.sample.project.core.PostDetail
import com.sample.project.core.PostList
import com.sample.project.core.rememberAppNavBackStack
import com.sample.project.presentation.postDetail.PostDetailRoute
import com.sample.project.presentation.postList.PostListRoute

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
                    PostListRoute(backStack)
                }

                is PostDetail -> NavEntry(key) {

                    PostDetailRoute(key.id)
                }

                else -> {
                    error("Unknown route: $key")
                }
            }
        }
    }


}