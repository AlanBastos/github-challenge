package com.example.githubchallenge.model

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

data class PullRequest(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("user") val user: User?,
    @SerializedName("created_at") val created_at: String?,
    @SerializedName("body") val body: String?,
    @SerializedName("state") val state: String
):java.io.Serializable {

    fun getFormattedDate(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        val date = sdf.parse(created_at)
        val outPutFormat = SimpleDateFormat("dd/MM/yyyy - HH:mm", Locale.getDefault())
        return outPutFormat.format(date)
    }
}

