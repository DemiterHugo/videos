package com.example.imagevideos.ui.detail

import androidx.lifecycle.*
import com.example.imagevideos.model.network.apientities.ApiImage
import com.example.imagevideos.model.network.apientities.ApiVideo
import com.example.imagevideos.model.repositories.VideosRepository
import kotlinx.coroutines.launch

class VideoViewModel(private val image: ApiImage, private val videosRepository: VideosRepository): ViewModel() {

    private val _state = MutableLiveData(UiState(image = image))
    val state: LiveData<UiState> get(){
        if(_state.value?.videos == null) refresh()
    return _state
    }


    private fun refresh() {
        viewModelScope.launch {
            _state.value = UiState(loading = true, image = image)
            _state.value = UiState(videos = videosRepository.findRelatedVideos(image).hits, image = image)
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val videos: List<ApiVideo>?= null,
        val image: ApiImage
    )
}

@Suppress("UNCHECKED_CAST")
class VideoViewModelFactory(private val image: ApiImage, private val videosRepository: VideosRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VideoViewModel(image, videosRepository) as T
    }
}