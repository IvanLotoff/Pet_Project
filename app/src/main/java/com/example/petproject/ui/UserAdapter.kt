package com.example.petproject.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.data.User
import com.example.petproject.R
import com.example.petproject.databinding.FragmentMainBinding
import com.example.petproject.databinding.UserBinding

class UserAdapter : PagingDataAdapter<User, UserAdapter.ViewHolder>(DataExtinguisher) {

    object DataExtinguisher : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bindItem(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val dataBinding = DataBindingUtil.inflate<UserBinding>(
            layoutInflater,
            R.layout.user,
            parent,
            false
        )
        return ViewHolder(dataBinding)
    }

    class ViewHolder(private val binding: UserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(user: User?) {
            if(user == null)
                return
            binding.user = user
        }
    }
}