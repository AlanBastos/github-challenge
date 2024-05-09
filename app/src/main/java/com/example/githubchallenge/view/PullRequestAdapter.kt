package com.example.githubchallenge.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubchallenge.R
import com.example.githubchallenge.databinding.ItemPullrequestBinding
import com.example.githubchallenge.model.PullRequest
import java.text.SimpleDateFormat
import java.util.Locale

class PullRequestAdapter(private val pullRequests: MutableList<PullRequest>) :
    RecyclerView.Adapter<PullRequestAdapter.PullRequestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): PullRequestViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPullrequestBinding.inflate(inflater, parent, false)
        return PullRequestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        holder.bind(pullRequests[position])
    }

    override fun getItemCount(): Int {
        return pullRequests.size
    }

    class PullRequestViewHolder(private val binding: ItemPullrequestBinding) :
    RecyclerView.ViewHolder(binding.root){

        fun bind(pullRequest: PullRequest) {
            binding.pullrequest = pullRequest
            binding.executePendingBindings()

            binding.prTvUsername.text = pullRequest.user.login
            binding.prTvPullRequestTitle.text = pullRequest.title
            val createdAt = pullRequest.created_at
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            val date = sdf.parse(createdAt)
            val outPutFormat = SimpleDateFormat("dd/MM/yyyy - HH:mm", Locale.getDefault())
            val formattedDate = outPutFormat.format(date)
            binding.prTvCreateAt.text = "Criado em : "+formattedDate
            binding.prTvBodyPullRequest.text = pullRequest.body

            Glide.with(itemView.context)
                .load(pullRequest.user.avatar_url)
                .circleCrop()
                .placeholder(R.drawable.baseline_person_24)
                .into(binding.prAvatar)
        }

    }

    fun addAll(pullRequestsList: List<PullRequest>) {
        pullRequests.addAll(pullRequestsList)
        notifyDataSetChanged()
    }

}