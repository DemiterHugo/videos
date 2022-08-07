package com.example.imagevideos.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.imagevideos.R
import com.example.imagevideos.databinding.FragmentMainBinding
import com.example.imagevideos.model.network.apientities.ApiImage
import com.example.imagevideos.model.repositories.ImagesRepository
import com.example.imagevideos.ui.common.collect
import com.example.imagevideos.ui.common.visible
import com.example.imagevideos.ui.detail.VideoFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(ImagesRepository(requireActivity().application))
    }
    private lateinit var mainState: MainState
    private val imageAdapter = ImagesAdapter{ mainState.onImageClicked(it)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainState = buildMainState()
        val binding = FragmentMainBinding.bind(view)
        binding.idRecyclerImage.adapter = imageAdapter

        viewLifecycleOwner.collect(viewModel.state){
            binding.updateUI(it)
        }

        mainState.requestLocationPermission {
            viewModel.onUiReady()
        }
    }

    private fun FragmentMainBinding.updateUI(state: MainViewModel.UiState){
        idProgressImage.visible = state.loading
        state.images?.let{imageAdapter.submitList(it)}
    }
}




