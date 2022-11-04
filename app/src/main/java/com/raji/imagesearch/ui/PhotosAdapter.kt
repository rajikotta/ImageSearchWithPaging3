package com.raji.imagesearch.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.raji.imagesearch.R
import com.raji.imagesearch.data.models.UnsplashPhoto
import com.raji.imagesearch.databinding.ItemPhotoBinding

class PhotosAdapter :
    PagingDataAdapter<UnsplashPhoto, PhotosAdapter.PhotosViewHolder>(PHOTO_COMPARATOR) {
    inner class PhotosViewHolder(private val binding: ItemPhotoBinding) : ViewHolder(binding.root) {
        @SuppressLint("PrivateResource")
        fun onBind(photo: UnsplashPhoto) {
            binding.apply {
                Glide.with(itemView).load(photo.urls.regular).centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(com.google.android.material.R.drawable.mtrl_ic_error).into(imageView)
                textViewUserName.text = photo.user.name
            }
        }
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<UnsplashPhoto>() {
            override fun areItemsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: UnsplashPhoto,
                newItem: UnsplashPhoto
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        return PhotosViewHolder(
            ItemPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}