package com.example.githubchallenge.di

import com.example.githubchallenge.utils.DialogHelper
import com.example.githubchallenge.utils.NetworkConnection
import com.example.githubchallenge.utils.UIUtils
import com.example.githubchallenge.view.PullRequestAdapter
import com.example.githubchallenge.view.RepositoryAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object AppModule {

    @Provides
    fun provideRepositoryAdapter(): RepositoryAdapter {
        return RepositoryAdapter(mutableListOf())
    }

    @Provides
    fun providePullRequestAdapter(): PullRequestAdapter {
        return PullRequestAdapter(mutableListOf())
    }

    @Provides
    fun provideUIUtils(): UIUtils {
        return UIUtils
    }

    @Provides
    fun provideVerifyNetworkConnection(): NetworkConnection {
        return NetworkConnection
    }

    @Provides
    fun provideDialog() : DialogHelper {
        return DialogHelper
    }
}