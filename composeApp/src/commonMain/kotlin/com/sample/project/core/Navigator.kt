package com.sample.project.core

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
fun rememberAppNavBackStack() =
    rememberNavBackStack(
        configuration = navConfiguration()
    ).apply {
        if (isEmpty()) {
            add(PostList)
        }
    }

fun navConfiguration() = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(PostList::class, PostList.serializer())
            subclass(PostDetail::class, PostDetail.serializer())
        }
    }
}