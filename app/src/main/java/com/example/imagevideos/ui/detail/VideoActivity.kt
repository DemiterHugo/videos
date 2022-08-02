package com.example.imagevideos.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.imagevideos.R
import com.example.imagevideos.databinding.ActivityVideoBinding
import com.example.imagevideos.model.repositories.VideosRepository
import com.example.imagevideos.ui.common.loadUrl
import com.example.imagevideos.ui.common.visible
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state.collect{
                    updateUI(it)
                }
            }
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