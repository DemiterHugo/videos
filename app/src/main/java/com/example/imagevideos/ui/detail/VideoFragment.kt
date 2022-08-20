package com.example.imagevideos.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.imagevideos.R
import com.example.imagevideos.databinding.FragmentVideoBinding
import com.example.imagevideos.ui.common.collectFlow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoFragment : Fragment(R.layout.fragment_video) {

    private val viewModel: VideoViewModel by viewModels()

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
}