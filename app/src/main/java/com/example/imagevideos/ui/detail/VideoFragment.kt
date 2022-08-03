package com.example.imagevideos.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.imagevideos.R
import com.example.imagevideos.databinding.FragmentVideoBinding
import com.example.imagevideos.model.repositories.VideosRepository
import com.example.imagevideos.ui.common.loadUrl
import com.example.imagevideos.ui.common.visible
import kotlinx.coroutines.launch

class VideoFragment : Fragment(R.layout.fragment_video) {

    private val safeArgs: VideoFragmentArgs by navArgs()
    private val viewModel: VideoViewModel by viewModels {
        VideoViewModelFactory(requireNotNull(safeArgs.image), VideosRepository(requireActivity() as AppCompatActivity))
    }
    private val videoAdapter = VideosAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentVideoBinding.bind(view)
        binding.idRecyclerVideo.adapter = videoAdapter
        binding.idToolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
            //findNavController().popBackStack()
        }


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state.collect(){
                    binding.updateUI(it)
                }
            }
        }
    }

    private fun FragmentVideoBinding.updateUI(state: VideoViewModel.UiState){

            idToolbar.title = state.image.tags
            idImageHeader.loadUrl(state.image.previewURL)
            idInfoImage.setImage(state.image)
            idProgressVideo.visible = state.loading
            state.videos?.let { videoAdapter.submitList(it) }
    }
}