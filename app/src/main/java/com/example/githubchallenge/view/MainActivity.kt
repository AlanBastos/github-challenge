package com.example.githubchallenge.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubchallenge.R
import com.example.githubchallenge.contract.RepositoryContract
import com.example.githubchallenge.databinding.ActivityMainBinding
import com.example.githubchallenge.model.PullRequest
import com.example.githubchallenge.model.Repository
import com.example.githubchallenge.presenter.GithubRepository
import com.example.githubchallenge.presenter.RepositoryPresenter
import com.example.githubchallenge.utils.DialogHelper
import com.example.githubchallenge.utils.NetworkConnection
import com.example.githubchallenge.utils.RecyclerViewItemDecoration
import com.example.githubchallenge.utils.UIUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), RepositoryContract.View {

    @Inject lateinit var uiUtils: UIUtils
    @Inject lateinit var networkConnection: NetworkConnection
    @Inject lateinit var dialogHelper: DialogHelper
    @Inject lateinit var adapter: RepositoryAdapter

    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: RepositoryContract.Presenter
    private var isLoading: Boolean = false
    private var currentPage: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeUI()
        presenter = createPresenter()
        presenter.getJavaPopRepositories(currentPage)
    }

    private fun initializeUI() {
        uiUtils.setupUI(this)
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
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(RecyclerViewItemDecoration(
                this@MainActivity,
                R.drawable.recyclerview_divider
            ))
            adapter = this@MainActivity.adapter
        }

        val onClickListener = object : RepositoryAdapter.OnClickListener {
            override fun onClick(position: Int, repository: Repository) {
                val intent = Intent(this@MainActivity, PullRequestActivity::class.java).apply {
                    putExtra("repository", repository)
                }
                startActivity(intent)
            }
        }

        adapter.setOnClickListener(onClickListener)

        binding.recyclerView.addOnScrollListener(createOnScrollListener())
    }

    private fun createOnScrollListener(): RecyclerView.OnScrollListener {
        return object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount
                val visibleThreshold = 5 // Quantidade de itens para carregar antes do fim da lista

                if (!isLoading && lastVisibleItemPosition + visibleThreshold >= totalItemCount) {
                    isLoading = true
                    currentPage++ // Incrementando currentPage para a próxima página
                    presenter.getJavaPopRepositories(currentPage)
                }
            }
        }
    }

    private fun createPresenter(): RepositoryContract.Presenter {
        return RepositoryPresenter(this, GithubRepository())
    }

    override fun showRepositories(repositories: List<Repository>) {
        adapter.addAll(repositories)
        isLoading = false
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        isLoading = false
    }

    override fun showPullRequests(pullRequests: List<PullRequest>) {
        // Not implemented yet
    }
}