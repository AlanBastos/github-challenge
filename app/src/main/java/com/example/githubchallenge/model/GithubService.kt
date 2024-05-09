package com.example.githubchallenge.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("search/repositories?")
    fun getJavaPopRepositories(
        @Query("q") language: String = "language:java",
        @Query("sort") sort: String = "stars",
        @Query("page") page: Int
    ): Call<RepositoryResponse>

    @GET("repos/{owner}/{repo}/pulls")
    fun getPullRequests(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Query("state") state: String = "all",
        @Query("page") page: Int = 1
    ): Call<List<PullRequest>>

}