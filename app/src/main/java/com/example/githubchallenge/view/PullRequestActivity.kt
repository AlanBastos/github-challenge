package com.example.githubchallenge.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubchallenge.R
import com.example.githubchallenge.databinding.ActivityPullRequestBinding

class PullRequestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPullRequestBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)
    }
}