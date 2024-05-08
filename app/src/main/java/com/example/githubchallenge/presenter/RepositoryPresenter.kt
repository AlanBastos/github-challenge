package com.example.githubchallenge.presenter

import com.example.githubchallenge.contract.RepositoryContract
import com.example.githubchallenge.model.GithubService
import com.example.githubchallenge.model.RepositoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryPresenter(private val view: RepositoryContract.View,
                          private val service: GithubService
) : RepositoryContract.Presenter {

    var isLoading = false
    var isLastPage = false
    var currentPage = 1

    override fun getJavaPopRepositories(page: Int) {
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

    }
}