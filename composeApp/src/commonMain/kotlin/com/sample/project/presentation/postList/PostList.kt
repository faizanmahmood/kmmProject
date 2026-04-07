package com.sample.project.presentation.postList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sample.project.domain.post.PostData

@Composable
fun PostListScreen(
    state: PostListUiStates,
    onEvent: (PostListEvent) -> Unit
) {

    LaunchedEffect(Unit) {
        onEvent(PostListEvent.LoadPosts)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (state.posts.isNotEmpty()) {
            LazyColumn {
                items(
                    count = state.posts.size,
                    key = { index -> state.posts[index].id }
                ) { index ->
                    Text(
                        text = state.posts[index].title,
                        modifier = Modifier.clickable {
                            onEvent(PostListEvent.OnPostClick(state.posts[index]))
                        }
                    )
                    Text("Sample Text added for commiting")
                }
            }
        }

        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        state.error?.let {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(it, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
        }
    }

//    when {
//        state.isLoading -> {
//            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                Text("Loading Data please wait", fontSize = 26.sp, fontWeight = FontWeight.Bold)
//            }
//        }
//
//        state.error != null ->{
//            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                Text(state.error, fontSize = 26.sp, fontWeight = FontWeight.Bold)
//            }
//        }
//
//        else -> {
//            LazyColumn {
//                items(
//                    count = state.posts.size,
//                    key = { index -> state.posts[index].id }
//                ) { index ->
//                    Text(
//                        text = state.posts[index].title,
//                        modifier = Modifier.clickable {
//                            onEvent(PostListEvent.OnPostClick(state.posts[index]))
//                        }
//                    )
//                }
//            }
//        }
//    }
}