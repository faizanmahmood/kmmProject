package com.sample.project.data.repository.postList

import com.sample.project.core.ApiResult
import com.sample.project.core.safeApiCall
import com.sample.project.data.remote.responses.post.PostListResponse
import com.sample.project.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostListRepositoryImp(val apiService: ApiService) : PostListRepository {
    override suspend fun getPosts(): Flow<ApiResult<List<PostListResponse>> >{
        return flow {
            emit(safeApiCall(Dispatchers.Default) { apiService.getPosts() })
        }
    }
}