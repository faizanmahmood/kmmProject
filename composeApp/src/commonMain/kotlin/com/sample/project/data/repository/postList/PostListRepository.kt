package com.sample.project.data.repository.postList

import com.sample.project.core.ApiResult
import com.sample.project.data.remote.responses.post.PostListResponse
import com.sample.project.domain.post.PostData
import kotlinx.coroutines.flow.Flow

interface PostListRepository {

    suspend fun getPosts() : Flow<ApiResult<List<PostListResponse>>>
}