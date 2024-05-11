package com.example.githubchallenge.utils

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import javax.inject.Singleton

@Singleton
object DialogHelper {
    fun showNoInternetDialog(activity: AppCompatActivity, title: String, message: String) {
        AlertDialog.Builder(activity)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                activity.finish()
            }
            .setCancelable(false)
            .show()
    }
}
