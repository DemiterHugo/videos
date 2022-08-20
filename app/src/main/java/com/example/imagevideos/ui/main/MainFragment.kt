package com.example.imagevideos.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.imagevideos.R
import com.example.imagevideos.data.AndroidPermissionChecker
import com.example.imagevideos.data.PlayServicesLocationDataSource
import com.example.imagevideos.data.repositories.ImagesRepository
import com.example.imagevideos.data.repositories.RegionRepository
import com.example.imagevideos.databinding.FragmentMainBinding
import com.example.imagevideos.data.database.RoomImageDataSource
import com.example.imagevideos.data.server.ServerImageDataSource
import com.example.imagevideos.ui.common.app
import com.example.imagevideos.ui.common.collectFlow
import com.example.imagevideos.usecases.GetImagesUseCase
import com.example.imagevideos.usecases.RequestImagesUseCase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var mainState: MainState
    private val imageAdapter = ImagesAdapter{ mainState.onImageClicked(it)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainState = buildMainState()
        val binding = FragmentMainBinding.bind(view)
        binding.idRecyclerImage.adapter = imageAdapter

        viewLifecycleOwner.collectFlow(viewModel.state){
            binding.loading = it.loading
            binding.images = it.images
            binding.error = it.error?.let {
                mainState.errorToString(it)
            }
        }

        mainState.requestLocationPermission {
            viewModel.onUiReady()
        }
    }


}




