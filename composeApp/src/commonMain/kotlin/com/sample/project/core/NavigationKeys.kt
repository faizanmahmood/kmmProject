package com.sample.project.core

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route : NavKey

@Serializable
data object PostList : Route

@Serializable
data class PostDetail(val id: Int) : Route