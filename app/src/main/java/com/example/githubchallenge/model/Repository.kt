package com.example.githubchallenge.model

import com.google.gson.annotations.SerializedName


data class Repository(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("full_name") val full_name: String,
    @SerializedName("description") val description: String,
    @SerializedName("stargazers_count") val stargazers_count: Long,
    @SerializedName("forks_count") val forks_count: Long,
    @SerializedName("owner") val owner: Owner,
):java.io.Serializable