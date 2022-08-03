package com.example.imagevideos.ui.main

import android.media.Image
import androidx.lifecycle.*
import com.example.imagevideos.model.network.apientities.ApiImage
import com.example.imagevideos.model.repositories.ImagesRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MainViewModel(private val imagesRepository: ImagesRepository): ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init { refresh()}

    private fun refresh() {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(images = imagesRepository.findImages().hits)
        }
    }

    fun onImageClicked(image: ApiImage){
            _state.value = _state.value.copy(imageTo = image)
    }

    fun onNavigationDone(){
        _state.value =_state.value.copy(imageTo = null)
    }

    data class UiState(
        val loading: Boolean = false,
        val images: List<ApiImage>? = null,
        val imageTo: ApiImage? = null
    )

}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val imagesRepository: ImagesRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(imagesRepository) as T
    }

}


