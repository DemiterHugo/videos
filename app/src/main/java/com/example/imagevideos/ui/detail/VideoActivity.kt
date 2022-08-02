package com.example.imagevideos.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.imagevideos.R
import com.example.imagevideos.databinding.ActivityVideoBinding
import com.example.imagevideos.model.repositories.VideosRepository
import com.example.imagevideos.ui.common.loadUrl
import com.example.imagevideos.ui.common.visible

class VideoActivity : AppCompatActivity() {

    companion object {
        const val IMAGE = "VideoActivity:image"
    }
    private val viewModel: VideoViewModel by viewModels {
        VideoViewModelFactory(requireNotNull(intent.getParcelableExtra(IMAGE)), VideosRepository(this))
    }
    private val videoAdapter = VideosAdapter()
    private lateinit var binding: ActivityVideoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.idRecyclerVideo.adapter = videoAdapter

        viewModel.state.observe(this){
            updateUI(it)
        }
    }

    private fun updateUI(state: VideoViewModel.UiState){
        with(binding){
            idToolbar.title = state.image.tags
            idImageHeader.loadUrl(state.image.previewURL)
            idInfoImage.setImage(state.image)
            idProgressVideo.visible = state.loading
            state.videos?.let { videoAdapter.submitList(it) }
        }

    }
}