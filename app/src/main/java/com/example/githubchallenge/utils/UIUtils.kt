package com.example.githubchallenge.utils

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.githubchallenge.R
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
}