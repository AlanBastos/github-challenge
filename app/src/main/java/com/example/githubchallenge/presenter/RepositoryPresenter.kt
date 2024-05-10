package com.example.githubchallenge.presenter

import com.example.githubchallenge.contract.RepositoryContract
import com.example.githubchallenge.model.PullRequest
import com.example.githubchallenge.model.RepositoryResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryPresenter(
    private val view: RepositoryContract.View,
    private val githubRepository: GithubRepository,
) : RepositoryContract.Presenter {


    var isLoading = false
    var isLastPage = false
    var currentPage = 1

    override fun getJavaPopRepositories(page: Int) {
        if (!isLoading) {
            isLoading = true
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = githubRepository.getGithubService().getJavaPopRepositories(page=page)
                    withContext(Dispatchers.Main) {
                        response.enqueue(object : Callback<RepositoryResponse> {
                            override fun onResponse(
                                call: Call<RepositoryResponse>,
                                response: Response<RepositoryResponse>
                            ) {
                                if (response.isSuccessful) {
                                    val repository = response.body()?.items ?: emptyList()
                                    view.showRepositories(repository)
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
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        view.showError(e.message ?: "Unknown error")
                        isLoading = false
                    }
                }
            }
        }
    }

    override fun getPullRequests(owner: String, repo: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = githubRepository.getGithubService().getPullRequests(owner = owner,
                    repo = repo)
                withContext(Dispatchers.Main) {
                    response.enqueue(object : Callback<List<PullRequest>> {
                        override fun onResponse(
                            call: Call<List<PullRequest>>,
                            response: Response<List<PullRequest>>
                        ) {
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
            }catch (e: Exception){
                withContext(Dispatchers.Main) {
                    view.showError(e.message ?: "Unknown error")
                }

            }
        }
    }
}