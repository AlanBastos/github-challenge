package com.example.githubchallenge.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubchallenge.R
import com.example.githubchallenge.databinding.ItemRepositoryBinding
import com.example.githubchallenge.model.Repository


class RepositoryAdapter(private val repositories: List<Repository>) :
    RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :
            RepositoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRepositoryBinding.inflate(inflater, parent,false)
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(repositories[position])
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    class RepositoryViewHolder(private val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(repository: Repository) {
            binding.repository = repository
            binding.executePendingBindings()

            binding.tvUsername.text = repository.owner.login
            binding.tvRepositoryName.text = repository.name
            binding.tvRepositoryDescription.text = repository.description
            binding.tvStars.text = repository.stargazers_count.toString()
            binding.tvFork.text = repository.forks_count.toString()

            Glide.with(itemView.context)
                .load(repository.owner.avatar_url)
                .circleCrop()
                .placeholder(R.drawable.baseline_person_24)
                .into(binding.avatar)

        }
    }

}
