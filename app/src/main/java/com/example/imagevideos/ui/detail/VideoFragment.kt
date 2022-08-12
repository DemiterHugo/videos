package com.example.imagevideos.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.imagevideos.R
import com.example.imagevideos.databinding.FragmentVideoBinding
import com.example.imagevideos.model.repositories.ImagesRepository
import com.example.imagevideos.ui.common.app
import com.example.imagevideos.ui.common.collectFlow

class VideoFragment : Fragment(R.layout.fragment_video) {

    private val safeArgs: VideoFragmentArgs by navArgs()
    private val viewModel: VideoViewModel by viewModels {
        VideoViewModelFactory(
            safeArgs.imageId, ImagesRepository(requireActivity().app)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentVideoBinding.bind(view)
        binding.idToolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()                   //findNavController().popBackStack()
        }
        //Log.i("EL VALOR DE IMAGEID","${safeArgs.imageId}")

        viewLifecycleOwner.collectFlow(viewModel.state){
                if (it.image != null) {
                    binding.image = it.image
                }
        }
    }

    /*private fun FragmentVideoBinding.updateUI(state: VideoViewModel.UiState){

            idToolbar.title = state.image.title
            idImageHeader.loadUrl(state.image.imageUrl)
            idInfoImage.setImage(state.image)
            idProgressVideo.visible = state.loading
            state.videos?.let { videoAdapter.submitList(it) }
    }*/
}