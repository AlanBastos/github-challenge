package com.example.githubchallenge.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val id: Long,
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatar_url: String
):java.io.Serializable
