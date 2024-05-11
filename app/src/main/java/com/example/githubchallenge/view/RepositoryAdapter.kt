package com.example.githubchallenge.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubchallenge.R
import com.example.githubchallenge.databinding.ItemRepositoryBinding
import com.example.githubchallenge.model.Repository
import com.example.githubchallenge.utils.FormatToK.Companion.formatToK
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryAdapter @Inject constructor(private val repositories: MutableList<Repository>) :
    RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {


    private var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :
            RepositoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRepositoryBinding.inflate(inflater, parent,false)
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repository = repositories[position]
        holder.tvUsername.text = repository.owner.login
        holder.tvRepositoryName.text = repository.name
        holder.tvRepositoryDescription.text = repository.description

        val starText = holder.itemView.context.resources.getString(
            R.string.stars_count, repository.stargazers_count.formatToK().replace(
                ",","."
            )
        )
        val forkText = holder.itemView.context.resources.getString(
            R.string.fork_count, repository.forks_count.formatToK().replace(
                ",","."
            )
        )
        holder.tvStars.text = starText
        holder.tvFork.text = forkText

        val avatarImg = repository.owner.avatar_url
        Glide.with(holder.itemView.context)
            .load(avatarImg)
            .circleCrop()
            .placeholder(R.drawable.baseline_person_24)
            .into(holder.avatarImg)

        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, repository)
            }
        }
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    class RepositoryViewHolder(binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val tvUsername = binding.tvUsername
        val tvRepositoryName = binding.tvRepositoryName
        val tvRepositoryDescription = binding.tvRepositoryDescription
        val tvStars = binding.tvStars
        val tvFork = binding.tvFork
        val avatarImg = binding.avatar

    }

    fun addAll(newRepositories: List<Repository>) {
        repositories.addAll(newRepositories)
        notifyDataSetChanged()
    }

    // A function to bind the onclickListener.
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    // onClickListener Interface
    interface OnClickListener {
        fun onClick(position: Int, repository: Repository)
    }

}
