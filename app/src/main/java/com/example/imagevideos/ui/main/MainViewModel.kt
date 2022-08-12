package com.example.imagevideos.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.imagevideos.model.database.Image
import com.example.imagevideos.model.repositories.ImagesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val imagesRepository: ImagesRepository): ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            imagesRepository.getImages.collect{
                _state.value = UiState(images = it)
            }
        }
    }

     fun onUiReady() {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            imagesRepository.requestImages()
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val images: List<Image>? = null
    )
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val imagesRepository: ImagesRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(imagesRepository) as T
    }
}


