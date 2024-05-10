package com.example.githubchallenge.presenter

import com.example.githubchallenge.contract.RepositoryContract
import com.example.githubchallenge.model.GithubService
import com.example.githubchallenge.model.PullRequest
import com.example.githubchallenge.model.RepositoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryPresenter(private val view: RepositoryContract.View,
    private val githubRepository: GithubRepository
) : RepositoryContract.Presenter {

    var isLoading = false
    var isLastPage = false
    var currentPage = 1

    override fun getJavaPopRepositories(page: Int) {
        val service = githubRepository.getGithubService()
        if (!isLoading) {
            isLoading = true
            service.getJavaPopRepositories(page = 1).enqueue(object : Callback<RepositoryResponse> {

                override fun onResponse(
                    call: Call<RepositoryResponse>,
                    response: Response<RepositoryResponse>
                ) {
                    if (response.isSuccessful) {
                        val repositories = response.body()?.items ?: emptyList()
                        view.showRepositories(repositories)
                        isLoading = false
                        currentPage++
                    } else {
                        view.showError("Failed to fetch repositories")
                    }
                }

                override fun onFailure(call: Call<RepositoryResponse>, t: Throwable) {
                    view.showError(t.message ?: "Unknown error")
                    isLoading = false
                }


            })
        }
    }

    override fun getPullRequests(owner: String, repo: String) {
        val service = githubRepository.getGithubService()
        service.getPullRequests(owner,repo).enqueue(object : Callback<List<PullRequest>>{
            override fun onResponse(call: Call<List<PullRequest>>, response: Response<List<PullRequest>>) {
                if (response.isSuccessful) {
                    val pullRequest = response.body() ?: emptyList()
                    view.showPullRequests(pullRequest)
                } else {
                    view.showError("Failed to fetch pull requests")
                }
            }

            override fun onFailure(call: Call<List<PullRequest>>, t: Throwable) {
                view.showError(t.message ?: "Unknown error")
            }

        })
    }
}