package com.sample.project.presentation.postList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sample.nativeconfig.NativeConfigKeys

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
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp).clickable {
                            onEvent(PostListEvent.OnPostClick(state.posts[index]))
                        }
                    )
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
                Column {

                    Text(it, fontSize = 20.sp, fontWeight = FontWeight.Bold)

                    Spacer(modifier = Modifier.height(16.dp))

                    Text("Base Url " + NativeConfigKeys.getBaseUrl(), fontSize = 20.sp, fontWeight = FontWeight.Bold)

                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Client Id " + NativeConfigKeys.getClientId(), fontSize = 20.sp, fontWeight = FontWeight.Bold)

                    Spacer(modifier = Modifier.height(16.dp))
                    Text("API key " + NativeConfigKeys.getApiKey(), fontSize = 20.sp, fontWeight = FontWeight.Bold)

                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Secret Key " + NativeConfigKeys.getSecretKey(), fontSize = 20.sp, fontWeight = FontWeight.Bold)

                    Spacer(modifier = Modifier.height(16.dp))
                    Text("An other key " + NativeConfigKeys.getAnotherKey(), fontSize = 20.sp, fontWeight = FontWeight.Bold)

                }
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