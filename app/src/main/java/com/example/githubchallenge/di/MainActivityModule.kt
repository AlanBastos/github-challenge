package com.example.githubchallenge.di


import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubchallenge.R
import com.example.githubchallenge.contract.RepositoryContract
import com.example.githubchallenge.presenter.GithubRepository
import com.example.githubchallenge.presenter.RepositoryPresenter
import com.example.githubchallenge.utils.DialogHelper
import com.example.githubchallenge.utils.NetworkConnection
import com.example.githubchallenge.utils.RecyclerViewItemDecoration
import com.example.githubchallenge.utils.UIUtils
import com.example.githubchallenge.view.MainActivity
import com.example.githubchallenge.view.RepositoryAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object MainActivityModule {

    @Provides
    fun providePresenter(repositoryPresenter: RepositoryPresenter): RepositoryContract.Presenter {
        return repositoryPresenter
    }

    @Provides
    fun provideRecyclerView(activity: MainActivity): RecyclerView {
        val recyclerView = RecyclerView(activity)
        recyclerView.layoutManager = provideLinearLayoutManager(activity)
        recyclerView.addItemDecoration(RecyclerViewItemDecoration(activity,
            R.drawable.recyclerview_divider))
        return recyclerView
    }

    @Provides
    fun provideLinearLayoutManager(activity: MainActivity): RecyclerView.LayoutManager {
        return LinearLayoutManager(
            activity,
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    @Provides
    fun provideRepositoryAdapter(): RepositoryAdapter {
        return RepositoryAdapter(mutableListOf())
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

    @Provides
    fun provideRepositoryView(mainActivity: MainActivity): RepositoryContract.View {
        return mainActivity
    }

    @Provides
    fun provideGithubRepository(): GithubRepository {
        return GithubRepository()
    }


}