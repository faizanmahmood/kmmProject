package com.sample.project.presentation.postDetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sample.nativeconfig.NativeConfigKeys

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
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Detail Screen $postId", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Base Url: ${NativeConfigKeys.getBaseUrl()}", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "EP Location: ${NativeConfigKeys.getClientId()}", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Public Key: ${NativeConfigKeys.getApiKey()}", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "EP Customer: ${NativeConfigKeys.getSecretKey()}", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "EP Auth: ${NativeConfigKeys.getAnotherKey()}", fontSize = 20.sp)
        }

    }

}