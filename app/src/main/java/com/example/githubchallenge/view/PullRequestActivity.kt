package com.example.githubchallenge.view

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
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
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.actionbarBg)))
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24)

//        binding.tvPrOpen
//        binding.tvPrClosed

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

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1) && !(presenter as RepositoryPresenter).isLoading) {
                    presenter.getPullRequests(repository.owner.login, repository.name)
                }
            }
        })
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
}