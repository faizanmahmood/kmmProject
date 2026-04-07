package com.sample.project.presentation.postList

import androidx.lifecycle.ViewModel
import com.sample.project.core.ApiResult
import com.sample.project.data.repository.postList.PostListRepository
import com.sample.project.domain.post.toPostData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostListViewModel(
    private val repository: PostListRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(PostListUiStates(isLoading = false))
    val uiState: StateFlow<PostListUiStates> = _uiState

    private val _events = Channel<PostListEvent>()
    val events =_events.receiveAsFlow()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun onEvent(event: PostListEvent) {
        when (event) {
            is PostListEvent.LoadPosts -> {
                loadPosts()

            }
            is PostListEvent.OnPostClick -> {
                coroutineScope.launch {
                    _events.send(PostListEvent.OnPostClick(event.post))
                }
            }
        }
    }

    private fun loadPosts() {
        coroutineScope.launch {
            repository.getPosts().collect { result ->
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
                                posts = result.data.map { data -> data.toPostData() }
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
