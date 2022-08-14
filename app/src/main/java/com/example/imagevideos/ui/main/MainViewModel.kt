package com.example.imagevideos.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.imagevideos.model.Error
import com.example.imagevideos.model.database.Image
import com.example.imagevideos.model.repositories.ImagesRepository
import com.example.imagevideos.model.toError
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(private val imagesRepository: ImagesRepository): ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            imagesRepository.getImages
                .catch { cause -> _state.update {it.copy(error = cause.toError())} }
                .collect{images -> _state.update { UiState(images = images)}}
        }
    }

     fun onUiReady() {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            val error = imagesRepository.requestImages()
            _state.update { it.copy(loading = false, error = error)}
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val images: List<Image>? = null,
        val error: Error? = null
    )
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val imagesRepository: ImagesRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(imagesRepository) as T
    }
}


