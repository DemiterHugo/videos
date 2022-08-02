package com.example.imagevideos.ui.main

import androidx.lifecycle.*
import com.example.imagevideos.model.network.apientities.ApiImage
import com.example.imagevideos.model.repositories.ImagesRepository
import kotlinx.coroutines.launch

class MainViewModel(private val imagesRepository: ImagesRepository): ViewModel() {

    private val _state = MutableLiveData(UiState())
    val state: LiveData<UiState> get(){
        if (_state.value?.images == null){
            refresh()
        }
        return _state
    }

    private fun refresh() {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(images = imagesRepository.findImages().hits)
        }

    }

    fun onImageClicked(image: ApiImage){
        _state.value = _state.value?.copy(imageNavigate = image)
    }

    data class UiState(
        val loading: Boolean = false,
        val images: List<ApiImage>? = null,
        val imageNavigate: ApiImage? = null
    )
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val imagesRepository: ImagesRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(imagesRepository) as T
    }

}


