package com.example.imagevideos.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.imagevideos.R
import com.example.imagevideos.usecases.FindImagesByIdUseCase
import com.example.imagevideos.usecases.SwitchImageFavoriteUseCase
import com.example.imagevideos.data.repositories.ImagesRepository
import com.example.imagevideos.data.repositories.RegionRepository
import com.example.imagevideos.databinding.FragmentVideoBinding
import com.example.imagevideos.framework.database.RoomImageDataSource
import com.example.imagevideos.framework.server.ServerImageDataSource
import com.example.imagevideos.ui.common.app
import com.example.imagevideos.ui.common.collectFlow

class VideoFragment : Fragment(R.layout.fragment_video) {

    private val safeArgs: VideoFragmentArgs by navArgs()
    private val viewModel: VideoViewModel by viewModels {
        val regionRepository = RegionRepository(requireActivity().app)
        val localImageDataSource = RoomImageDataSource(requireActivity().app.db.imageDao())
        val remoteImageDataSource = ServerImageDataSource(getString(R.string.api_key),getString(R.string.image_type),true)
        val imagesRepository = ImagesRepository(regionRepository,localImageDataSource,remoteImageDataSource,)
        VideoViewModelFactory(
            safeArgs.imageId,
            FindImagesByIdUseCase(imagesRepository),
            SwitchImageFavoriteUseCase(imagesRepository)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentVideoBinding.bind(view)
        binding.idToolbar.setNavigationOnClickListener { requireActivity().onBackPressed()}                   //findNavController().popBackStack() }
        binding.idImageFavorite.setOnClickListener { viewModel.onFavoriteClicked() }
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