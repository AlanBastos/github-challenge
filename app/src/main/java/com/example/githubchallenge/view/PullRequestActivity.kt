package com.example.githubchallenge.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubchallenge.R
import com.example.githubchallenge.contract.RepositoryContract
import com.example.githubchallenge.databinding.ActivityPullRequestBinding
import com.example.githubchallenge.model.PullRequest
import com.example.githubchallenge.model.Repository
import com.example.githubchallenge.presenter.GithubRepository
import com.example.githubchallenge.presenter.RepositoryPresenter
import com.example.githubchallenge.utils.RecyclerViewItemDecoration


class PullRequestActivity : AppCompatActivity(), RepositoryContract.View {

    private lateinit var binding: ActivityPullRequestBinding
    private lateinit var presenter: RepositoryContract.Presenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PullRequestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPullRequestBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val githubRepository = GithubRepository()

        val repository: Repository? = intent.getSerializableExtra("repository") as Repository?
        if (repository != null) {
            supportActionBar!!.title = repository.name.replaceFirstChar(Char::uppercaseChar)
        } else {
            Toast.makeText(this, "Repository object not found", Toast.LENGTH_SHORT).show()
        }


        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView = binding.prRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PullRequestAdapter(mutableListOf())
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(RecyclerViewItemDecoration(this, R.drawable.recyclerview_divider))
        adapter.notifyDataSetChanged()
        recyclerView.adapter = adapter


        presenter = RepositoryPresenter(this, githubRepository)
        presenter.getPullRequests(repository!!.owner.login,repository.name )
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun showRepositories(repositories: List<Repository>) {
    }

    override fun showError(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

    override fun showPullRequests(pullRequests: List<PullRequest>) {
        adapter.addAll(pullRequests)
    }
}