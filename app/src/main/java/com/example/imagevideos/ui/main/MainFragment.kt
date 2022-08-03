package com.example.imagevideos.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.imagevideos.R
import com.example.imagevideos.databinding.FragmentMainBinding
import com.example.imagevideos.model.network.apientities.ApiImage
import com.example.imagevideos.model.repositories.ImagesRepository
import com.example.imagevideos.ui.common.visible
import com.example.imagevideos.ui.detail.VideoFragment
import kotlinx.coroutines.launch

class MainFragment : Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels { MainViewModelFactory(ImagesRepository(requireActivity() as AppCompatActivity))}
    private val imageAdapter = ImagesAdapter{ viewModel.onImageClicked(it)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainBinding.bind(view)

        binding.idRecyclerImage.adapter = imageAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state.collect{
                    binding.updateUI(it)
                }
            }
        }
    }

    private fun FragmentMainBinding.updateUI(state: MainViewModel.UiState){
        idProgressImage.visible = state.loading
        state.images?.let{imageAdapter.submitList(it)}
        state.imageNavigate?.let { navigateTo(it) }
    }

    private fun navigateTo(image: ApiImage){
        val navAction = MainFragmentDirections.actionMainToVideo(image)
        findNavController().navigate(navAction)
        //findNavController().navigate(R.id.action_main_to_video, bundleOf(VideoFragment.IMAGE to image))
    }
}