package com.example.githubchallenge.model

import com.google.gson.annotations.SerializedName

data class PullRequest(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("user") val user: User,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("body") val body: String
):java.io.Serializable
