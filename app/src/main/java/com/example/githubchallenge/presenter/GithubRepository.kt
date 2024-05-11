package com.example.githubchallenge.presenter

import com.example.githubchallenge.model.GithubService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GithubRepository {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    suspend fun getGithubService(): GithubService {
        return retrofit.create(GithubService::class.java)
    }

    companion object {
        private const val BASE_URL = "https://api.github.com/"
    }
}