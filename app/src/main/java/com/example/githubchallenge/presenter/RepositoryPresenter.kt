package com.example.githubchallenge.presenter

import com.example.githubchallenge.contract.RepositoryContract

class RepositoryPresenter(private val view: RepositoryContract.View,
    private val service: GithubService) : RepositoryContract.Presenter {



    override fun getJavaPopRepositories(page: Int) {
        TODO("Not yet implemented")
    }

    override fun getPullRequests(owner: String, repo: String) {
        TODO("Not yet implemented")
    }
}