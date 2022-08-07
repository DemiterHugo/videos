package com.example.imagevideos.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.imagevideos.model.network.apientities.ApiImage
import com.example.imagevideos.model.repositories.ImagesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val imagesRepository: ImagesRepository): ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

     fun onUiReady() {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(images = imagesRepository.findImages().hits)
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val images: List<ApiImage>? = null
    )
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val imagesRepository: ImagesRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(imagesRepository) as T
    }
}


