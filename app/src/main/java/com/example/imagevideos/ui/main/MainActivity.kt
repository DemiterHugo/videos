package com.example.imagevideos.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.imagevideos.databinding.ActivityMainBinding
import com.example.imagevideos.model.network.apientities.ApiImage
import com.example.imagevideos.model.repositories.ImagesRepository
import com.example.imagevideos.ui.common.visible
import com.example.imagevideos.ui.detail.VideoActivity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels { MainViewModelFactory(ImagesRepository(this))}
    private val imageAdapter = ImagesAdapter{ viewModel.onImageClicked(it)}
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.idRecyclerImage.adapter = imageAdapter

        //viewModel.state.observe(this,::updateUI)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state.collect{
                    updateUI(it)
                }
            }
        }
    }

    private fun updateUI(state: MainViewModel.UiState){
        binding.idProgressImage.visible = state.loading
        state.images?.let{imageAdapter.submitList(it)}
        state.imageNavigate?.let { navigateTo(it) }
    }

    private fun navigateTo(image: ApiImage){
        val intent = Intent(this, VideoActivity::class.java)
        intent.putExtra(VideoActivity.IMAGE,image)
        startActivity(intent)
    }
}