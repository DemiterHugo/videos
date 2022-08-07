package com.example.imagevideos.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.imagevideos.R
import com.example.imagevideos.databinding.ViewImageBinding
import com.example.imagevideos.model.network.apientities.ApiImage
import com.example.imagevideos.ui.common.inflate
import com.example.imagevideos.ui.common.loadUrl

class ImagesAdapter(private val listener: (ApiImage)-> Unit):
    ListAdapter<ApiImage,ImagesAdapter.ViewHolderImage>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderImage {
        val view = parent.inflate(R.layout.view_image,false)
        return ViewHolderImage(view)
    }

    override fun onBindViewHolder(holder: ViewHolderImage, position: Int) {
        val image = getItem(position)
        holder.bind(image)
        holder.itemView.setOnClickListener { listener(image) }
    }

    class ViewHolderImage(view: View): RecyclerView.ViewHolder(view){
        private val binding = ViewImageBinding.bind(view)
        fun bind(image: ApiImage) {
            with(binding){
                idImageView.loadUrl(image.webformatURL)
                idTextImage.text = image.tags
            }
        }
    }
}

private class DiffUtilCallback: DiffUtil.ItemCallback<ApiImage>() {
    override fun areItemsTheSame(oldItem: ApiImage, newItem: ApiImage): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ApiImage, newItem: ApiImage): Boolean {
        return oldItem == newItem
    }
}

//basicDiffUtil{old, new -> old.id == new.id}