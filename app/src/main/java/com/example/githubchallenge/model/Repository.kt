package com.example.githubchallenge.model


data class Repository(
    val id: Long,
    val name: String,
    val full_name: String,
    val description: String,
    val stargazers_count: Long,
    val forks_count: Long,
    val owner: Owner,

)
