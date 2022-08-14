package com.example.imagevideos.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.imagevideos.R
import com.example.imagevideos.databinding.FragmentMainBinding
import com.example.imagevideos.domain.GetImagesUseCase
import com.example.imagevideos.domain.RequestImagesUseCase
import com.example.imagevideos.model.Error
import com.example.imagevideos.model.repositories.ImagesRepository
import com.example.imagevideos.ui.common.app
import com.example.imagevideos.ui.common.collectFlow

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels {
        val repository = ImagesRepository(requireActivity().app)
        MainViewModelFactory(
            GetImagesUseCase(repository),
            RequestImagesUseCase(repository)
        )
    }
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




