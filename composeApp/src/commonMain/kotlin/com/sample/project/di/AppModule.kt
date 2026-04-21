package com.sample.project.di

import com.sample.project.data.repository.postDetail.PostDetailRepository
import com.sample.project.data.repository.postDetail.PostDetailRepositoryImp
import com.sample.project.data.repository.postList.PostListRepository
import com.sample.project.data.repository.postList.PostListRepositoryImp
import com.sample.project.network.ApiService
import com.sample.project.presentation.postDetail.PostDetailViewModel
import com.sample.project.presentation.postList.PostListViewModel
import io.ktor.client.HttpClient
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin() {
    val appModule = module {
        single {
            HttpClient(httpClientEngineFactory()) {
                install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation) {
                    json(
                        Json { ignoreUnknownKeys = true },
                    )
                }
            }
        }

        factory { PostListViewModel(get()) }
        factory { PostDetailViewModel(get()) }

        single { ApiService(get()) }

        single<PostListRepository> { PostListRepositoryImp(get()) }
        single<PostDetailRepository> { PostDetailRepositoryImp(get()) }

    }

    startKoin {
        modules(appModule)
    }
}
