package com.sample.project.presentation.postDetail

import com.sample.project.core.ApiResult
import com.sample.project.data.repository.postDetail.PostDetailRepository
import com.sample.project.domain.post.toPostData
import com.sample.project.presentation.postList.PostListEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostDetailViewModel(
    private val repository: PostDetailRepository
) {

    private val _uiState = MutableStateFlow(PostDetailUiStates(isLoading = true))
    val uiState: StateFlow<PostDetailUiStates> = _uiState

    private val _events = MutableSharedFlow<PostDetailEvent>()
    val events: SharedFlow<PostDetailEvent> = _events

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun onEvent(event: PostDetailEvent) {
        when (event) {
            is PostDetailEvent.LoadPost -> loadPost(event.id)
        }
    }

    private fun loadPost(id: Int) {
        coroutineScope.launch {
            repository.getPostById(id).collect { result ->
                when (result) {
                    is ApiResult.Loading -> {
                        _uiState.update {
                            it.copy(isLoading = true, error = null)
                        }
                    }

                    is ApiResult.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                post = result.data.toPostData()
                            )
                        }
                    }

                    is ApiResult.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = result.message
                            )
                        }
                    }
                }
            }
        }
    }
}