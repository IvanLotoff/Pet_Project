package com.example.petproject.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewGroupCompat
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.petproject.R
import com.example.petproject.databinding.LoadStateFooterBinding

class LoadingStateViewHolder(private val binding : LoadStateFooterBinding, retrier: () -> Unit): RecyclerView.ViewHolder(binding.root) {
    init {
        binding.retryButton.setOnClickListener {
            retrier.invoke()
        }
    }

    fun bind(loadState: LoadState){
        if(loadState is LoadState.Error)
            binding.errorMsg.text = "Что-то пошло не так, возможно нет интернета"
        with(binding){
            progressBar.isVisible = loadState is LoadState.Loading
            retryButton.isVisible = loadState is LoadState.Error
            errorMsg.isVisible = loadState is LoadState.Error
        }
    }

    companion object{
        fun create(parent: ViewGroup, retrier: () -> Unit): LoadingStateViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.load_state_footer, parent, false)
            val binding = LoadStateFooterBinding.bind(view)
            return LoadingStateViewHolder(binding, retrier)
        }
    }
}