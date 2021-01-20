package com.yashovardhan99.trelldemo.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.yashovardhan99.trelldemo.R
import com.yashovardhan99.trelldemo.databinding.CardUserBinding
import com.yashovardhan99.trelldemo.databinding.RecyclerUsersBinding

class UserAdapter(private val onFollow: (User) -> Unit) :
    ListAdapter<User, UserAdapter.UserViewHolder>(DiffUtils()) {
    class UserViewHolder(private val binding: CardUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User, onFollow: (User) -> Unit) {
            binding.user = user
            binding.followButton.setOnClickListener { onFollow(user) }
            binding.userImage.load(user.imageUrl) {
                crossfade(true)
                placeholder(R.drawable.circle_image_placeholder)
                transformations(CircleCropTransformation())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UserViewHolder(CardUserBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position), onFollow)
    }

    companion object {
        class DiffUtils : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class UserHolderAdapter(private val userAdapter: UserAdapter) :
    RecyclerView.Adapter<UserHolderAdapter.UserRecyclerHolder>() {
    class UserRecyclerHolder(private val binding: RecyclerUsersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userAdapter: UserAdapter) {
            binding.usersRecycler.adapter = userAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRecyclerHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UserRecyclerHolder(RecyclerUsersBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: UserRecyclerHolder, position: Int) {
        holder.bind(userAdapter)
    }

    override fun getItemCount(): Int {
        return 1
    }
}