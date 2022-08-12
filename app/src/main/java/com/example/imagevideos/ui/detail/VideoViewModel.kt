package com.example.imagevideos.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.imagevideos.model.database.Image
import com.example.imagevideos.model.repositories.ImagesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class VideoViewModel(
    private val imageId: Int,
    private val imagesRepository: ImagesRepository
): ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            imagesRepository.findImageById(imageId).collect{
                _state.value = UiState(image = it)
            }
        }
    }

    fun onFavoriteClicked() {
        viewModelScope.launch {
            _state.value.image?.let {
                imagesRepository.switchFavorite(it)  }

        }
    }

    class UiState(val image: Image? = null)
}

@Suppress("UNCHECKED_CAST")
class VideoViewModelFactory(
    private val imageId: Int,
    private val imagesRepository: ImagesRepository
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VideoViewModel(imageId, imagesRepository) as T
    }
}