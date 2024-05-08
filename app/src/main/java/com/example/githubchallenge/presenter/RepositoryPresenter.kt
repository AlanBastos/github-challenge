package com.example.githubchallenge.presenter

import com.example.githubchallenge.contract.RepositoryContract
import com.example.githubchallenge.model.GithubService

class RepositoryPresenter(private val view: RepositoryContract.View,
    private val service: GithubService
) : RepositoryContract.Presenter {

    private var isLoading = false
    private var isLastPage = false
    private var currentPage = 1

    override fun getJavaPopRepositories(page: Int) {
        if (!isLoading && isLastPage) {
            isLoading = true
            service.getJavaPopRepositories(page).enqueue()
        }
    }

    override fun getPullRequests(owner: String, repo: String) {
        TODO("Not yet implemented")
    }
}