package com.example.githubchallenge.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubchallenge.R
import com.example.githubchallenge.contract.RepositoryContract
import com.example.githubchallenge.databinding.ActivityMainBinding
import com.example.githubchallenge.model.GithubService
import com.example.githubchallenge.model.PullRequest
import com.example.githubchallenge.model.Repository
import com.example.githubchallenge.presenter.RepositoryPresenter
import com.example.githubchallenge.utils.RecyclerViewItemDecoration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), RepositoryContract.View {

    private lateinit var binding: ActivityMainBinding
    private lateinit var presenter: RepositoryContract.Presenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RepositoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = RepositoryAdapter(mutableListOf())

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

        presenter.getJavaPopRepositories(1)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!recyclerView.canScrollVertically(1) && !(presenter as RepositoryPresenter).isLoading) {
                    presenter.getJavaPopRepositories((presenter as RepositoryPresenter).currentPage + 1)
                }
            }
        })
    }

    override fun showRepositories(repositories: List<Repository>) {
        adapter.addAll(repositories)
    }

    override fun showError(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

    override fun showPullRequests(pullRequests: List<PullRequest>) {

    }
}