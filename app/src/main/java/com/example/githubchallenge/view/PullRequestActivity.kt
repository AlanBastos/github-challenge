package com.example.githubchallenge.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubchallenge.R
import com.example.githubchallenge.contract.RepositoryContract
import com.example.githubchallenge.databinding.ActivityPullRequestBinding
import com.example.githubchallenge.model.GithubService
import com.example.githubchallenge.model.PullRequest
import com.example.githubchallenge.model.Repository
import com.example.githubchallenge.presenter.RepositoryPresenter
import com.example.githubchallenge.utils.RecyclerViewItemDecoration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

        var repository: Repository? = null

         repository = intent.getSerializableExtra("repository") as Repository?
        if (repository != null) {
            // Faça o que for necessário com o objeto Repository
            // Por exemplo, exiba seus valores em TextViews
//            binding.tvRepositoryName.text = repository.name
//            binding.tvRepositoryDescription.text = repository.description
            // E assim por diante...
        } else {
            // Trate o caso em que o objeto Repository é nulo
            Toast.makeText(this, "Objeto Repository não encontrado", Toast.LENGTH_SHORT).show()
        }


        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recyclerView = binding.prRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = PullRequestAdapter(mutableListOf())

        recyclerView.layoutManager = layoutManager

        recyclerView.addItemDecoration(RecyclerViewItemDecoration(this, R.drawable.recyclerview_divider))

        adapter.notifyDataSetChanged()

        recyclerView.adapter = adapter

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(GithubService::class.java)
        presenter = RepositoryPresenter(this, service)

        presenter.getPullRequests(repository!!.owner.login,repository.name )
    }

    override fun showRepositories(repositories: List<Repository>) {
    }

    override fun showError(message: String) {
    }

    override fun showPullRequests(pullRequests: List<PullRequest>) {
        adapter.addAll(pullRequests)
    }
}