package com.example.githubchallenge.presenter

import com.example.githubchallenge.model.GithubService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GithubRepository {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getGithubService(): GithubService {
        return retrofit.create(GithubService::class.java)
    }

    companion object {
        private const val BASE_URL = "https://api.github.com/"
    }
}