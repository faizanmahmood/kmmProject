package com.sample.project.data.repository.postDetail

import com.sample.project.core.ApiResult
import com.sample.project.core.safeApiCall
import com.sample.project.data.remote.responses.comments.CommentListResponse
import com.sample.project.data.remote.responses.post.PostListResponse
import com.sample.project.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class PostDetailRepositoryImp(val apiService: ApiService) : PostDetailRepository {

    override suspend fun getPostById(id: Int): Flow<ApiResult<PostListResponse>> {
        return flow { emit(safeApiCall(Dispatchers.Default) { apiService.getPostsById(id) }) }
    }

    override suspend fun getPostComments(id: Int): Flow<ApiResult<List<CommentListResponse>>> {
        return flow { emit(safeApiCall(Dispatchers.Default) { apiService.getComments(id) }) }
    }
}