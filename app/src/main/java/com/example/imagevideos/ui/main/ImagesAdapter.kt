package com.example.imagevideos.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.imagevideos.R
import com.example.imagevideos.databinding.ViewImageBinding
import com.example.imagevideos.model.database.Image
import com.example.imagevideos.ui.common.inflate

class ImagesAdapter(private val listener: (Image)-> Unit):
    ListAdapter<Image,ImagesAdapter.ViewHolderImage>(DiffUtilCallback()) {

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
        fun bind(image: Image) {
            binding.image = image
        }
    }
}


private class DiffUtilCallback: DiffUtil.ItemCallback<Image>() {
    override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
        return oldItem == newItem
    }
}

//basicDiffUtil{old, new -> old.id == new.id}