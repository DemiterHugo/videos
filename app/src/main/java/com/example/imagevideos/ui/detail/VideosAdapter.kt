package com.example.imagevideos.ui.detail

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.imagevideos.R
import com.example.imagevideos.databinding.ViewVideoBinding
import com.example.imagevideos.model.database.Video
import com.example.imagevideos.ui.common.inflate

class VideosAdapter: ListAdapter<Video, VideosAdapter.ViewHolderVideo>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderVideo {
        val view = parent.inflate(R.layout.view_video, false)
        return ViewHolderVideo(view)
    }

    override fun onBindViewHolder(holder: ViewHolderVideo, position: Int) {
        val video = getItem(position)
        holder.bind(video)
    }

    class ViewHolderVideo(view: View): RecyclerView.ViewHolder(view){
        private val  binding = ViewVideoBinding.bind(view)
        fun bind(video: Video){
            binding.video = video
        }
    }
}

private class DiffUtilCallback: DiffUtil.ItemCallback<Video>() {
    override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
        return oldItem == newItem
    }
}