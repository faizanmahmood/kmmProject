package com.sample.project.data.repository.postDetail

import com.sample.project.core.ApiResult
import com.sample.project.data.remote.responses.comments.CommentListResponse
import com.sample.project.data.remote.responses.post.PostListResponse
import kotlinx.coroutines.flow.Flow

interface PostDetailRepository {

    suspend fun getPostById(id: Int): Flow<ApiResult<PostListResponse>>
    suspend fun getPostComments(id: Int): Flow<ApiResult<List<CommentListResponse>>>
}