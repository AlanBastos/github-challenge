package com.example.githubchallenge.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubchallenge.R
import com.example.githubchallenge.databinding.ItemRepositoryBinding
import com.example.githubchallenge.model.Repository
import com.example.githubchallenge.utils.FormatToK.Companion.formatToK


class RepositoryAdapter(private val repositories: MutableList<Repository>) :
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

            val starText = itemView.context.resources.getString(
                R.string.stars_count, repository.stargazers_count.formatToK().replace(
                    ",","."
                )
            )
            val forkText = itemView.context.resources.getString(
                R.string.fork_count, repository.stargazers_count.formatToK().replace(
                    ",","."
                )
            )
            binding.tvStars.text = starText
            binding.tvFork.text = forkText


            Glide.with(itemView.context)
                .load(repository.owner.avatar_url)
                .circleCrop()
                .placeholder(R.drawable.baseline_person_24)
                .into(binding.avatar)

        }
    }

    fun addAll(newRepositories: List<Repository>) {
        repositories.addAll(newRepositories)
        notifyDataSetChanged()
    }

}
