package com.raji.imagesearch.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView.*
import com.raji.imagesearch.databinding.ListFooterBinding

class PhotoLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<PhotoLoadStateAdapter.LoadStateViewHolder>() {
    inner class LoadStateViewHolder(private val binding: ListFooterBinding) :
        ViewHolder(binding.root) {
        init {
            binding.buttonRetry.setOnClickListener {retry.invoke() }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                buttonRetry.isVisible = loadState !is LoadState.Loading
                textViewError.isVisible = loadState !is LoadState.Loading
            }
        }
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)

    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(
            ListFooterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}