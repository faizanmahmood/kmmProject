package com.sample.project.di

import com.sample.project.data.repository.postDetail.PostDetailRepository
import com.sample.project.data.repository.postDetail.PostDetailRepositoryImp
import com.sample.project.data.repository.postList.PostListRepository
import com.sample.project.data.repository.postList.PostListRepositoryImp
import com.sample.project.network.ApiService
import com.sample.project.network.AuthTokenProvider
import com.sample.project.network.NetworkClientConfig
import com.sample.project.network.NoOpAuthTokenProvider
import com.sample.project.network.createNetworkHttpClient
import com.sample.project.presentation.postDetail.PostDetailViewModel
import com.sample.project.presentation.postList.PostListViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module


fun initKoin(networkClientConfig: NetworkClientConfig = NetworkClientConfig()) {
    val appModule = module {
        single<AuthTokenProvider> { NoOpAuthTokenProvider }
        single {
            val mergedConfig = networkClientConfig.copy(
                authTokenProvider = networkClientConfig.authTokenProvider ?: get<AuthTokenProvider>()
            )
            createNetworkHttpClient(mergedConfig)
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
