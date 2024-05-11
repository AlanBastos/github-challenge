package com.example.githubchallenge.utils

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.githubchallenge.R
import com.example.githubchallenge.model.Repository
import java.io.Serializable
import javax.inject.Singleton

@Singleton
object UIUtils {

    fun setupUI(activity: AppCompatActivity) {
        activity.supportActionBar?.apply {
            title = "Tendências do Github - Java"
            setBackgroundDrawable(
                ColorDrawable(
                    ContextCompat.getColor(
                activity.baseContext,
                R.color.actionbarBg))
            )
        }
    }

    fun setupUIWithBackButton(activity: AppCompatActivity) {
        val repository: Repository? = activity.intent.serializable("repository") as Repository?
        activity.supportActionBar?.apply {
            title = repository?.name?.replaceFirstChar(Char::uppercaseChar) ?: "Repository object not found"
            setDisplayHomeAsUpEnabled(true)
            setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(
                activity.baseContext,
                R.color.actionbarBg)))
            setHomeAsUpIndicator(
                R.drawable.baseline_arrow_back_ios_24
            )
        }
    }

    private inline fun <reified T : Serializable> Intent.serializable(key: String): T? = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializableExtra(key, T::class.java)
        else -> @Suppress("DEPRECATION") getSerializableExtra(key) as? T
    }
}