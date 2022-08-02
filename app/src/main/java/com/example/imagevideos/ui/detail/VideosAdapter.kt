package com.example.imagevideos.ui.detail

import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.imagevideos.R
import com.example.imagevideos.databinding.ViewVideoBinding
import com.example.imagevideos.model.network.apientities.ApiVideo
import com.example.imagevideos.ui.common.inflate
import kotlin.coroutines.coroutineContext

class VideosAdapter: ListAdapter<ApiVideo, VideosAdapter.ViewHolderVideo>(DiffUtilCallback()) {

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
        fun bind(video: ApiVideo){
            with(binding){
                idVideoView.setVideoPath(video.videos.tiny.url)
                idVideoView.start()
                //idVideoView.requestFocus()
                idTextVideo.text = video.tags
            }
        }
    }
}

private class DiffUtilCallback: DiffUtil.ItemCallback<ApiVideo>() {
    override fun areItemsTheSame(oldItem: ApiVideo, newItem: ApiVideo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ApiVideo, newItem: ApiVideo): Boolean {
        return oldItem == newItem
    }
}