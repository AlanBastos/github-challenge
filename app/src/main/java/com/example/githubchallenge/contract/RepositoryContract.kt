package com.example.githubchallenge.contract

import com.example.githubchallenge.model.PullRequest
import com.example.githubchallenge.model.Repository

interface RepositoryContract {
    interface View {
        fun showRepositories(repositories: List<Repository>)
        fun showError(message: String)
        fun showPullRequests(pullRequests: List<PullRequest>)
    }

    interface Presenter {
        fun getJavaPopRepositories(page: Int)
        fun getPullRequests(owner: String, repo: String)
    }
}