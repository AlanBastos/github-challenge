package com.example.githubchallenge.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubchallenge.R
import com.example.githubchallenge.contract.RepositoryContract
import com.example.githubchallenge.databinding.ActivityPullRequestBinding
import com.example.githubchallenge.model.PullRequest
import com.example.githubchallenge.model.Repository
import com.example.githubchallenge.presenter.GithubRepository
import com.example.githubchallenge.presenter.RepositoryPresenter
import com.example.githubchallenge.utils.DialogHelper
import com.example.githubchallenge.utils.NetworkConnection
import com.example.githubchallenge.utils.RecyclerViewItemDecoration
import com.example.githubchallenge.utils.UIUtils
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable
import javax.inject.Inject

@AndroidEntryPoint
class PullRequestActivity : AppCompatActivity(), RepositoryContract.View {

    @Inject lateinit var uiUtils: UIUtils
    @Inject lateinit var adapter: PullRequestAdapter
    @Inject lateinit var dialogHelper: DialogHelper
    @Inject lateinit var networkConnection: NetworkConnection

    private lateinit var binding: ActivityPullRequestBinding
    private lateinit var presenter: RepositoryContract.Presenter
    private lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPullRequestBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        presenter = createPresenter()
        initializeUI()
        setupRecyclerView()
        val repository = getRepositoryFromIntent()
        presenter.getPullRequests(repository.owner.login,repository.name )

    }

    private fun initializeUI() {
        uiUtils.setupUIWithBackButton(this)
        if (!networkConnection.isNetworkAvailable(this)) {
            dialogHelper.showNoInternetDialog(
                this,
                getString(R.string.dialog_title),
                getString(R.string.dialog_message)
            )
        }
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        binding.prRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(RecyclerViewItemDecoration(
                this@PullRequestActivity,
                R.drawable.recyclerview_divider
            ))
            adapter = this@PullRequestActivity.adapter
        }

        binding.prRecyclerView.addOnScrollListener(createOnScrollListener())
    }

    private fun createOnScrollListener(): RecyclerView.OnScrollListener {
        return object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)
                    && !(presenter as RepositoryPresenter).isLoading) {
                    presenter.getPullRequests(repository.owner.login, repository.name)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    private fun createPresenter(): RepositoryContract.Presenter {

        return RepositoryPresenter(this, GithubRepository())
    }

    override fun showRepositories(repositories: List<Repository>) {
    }

    override fun showError(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

    override fun showPullRequests(pullRequests: List<PullRequest>) {
        adapter.addAll(pullRequests)

        val pROpenCount = pullRequests.count { it.state == "open" }
        val pRClosedCount = pullRequests.count { it.state == "closed" }

        val openText = this.getString(R.string.pROpen, "$pROpenCount")
        val closedText = this.getString(R.string.pRClosed, "$pRClosedCount")
        binding.tvPrOpen.text = openText
        binding.tvPrClosed.text = closedText

        for (pullRequest in pullRequests) {
            Log.d("PullRequestState", "Title: ${pullRequest.title}, State: ${pullRequest.state}")
        }
    }

    private inline fun <reified T : Serializable> Intent.serializable(key: String): T? = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializableExtra(key, T::class.java)
        else -> @Suppress("DEPRECATION") getSerializableExtra(key) as? T
    }

    private fun getRepositoryFromIntent() : Repository {
        return intent.serializable("repository") as Repository? ?:
        throw IllegalAccessException("Repository not found in intent")
    }
}