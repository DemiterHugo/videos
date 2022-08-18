package com.example.imagevideos.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.imagevideos.data.toError
import com.example.imagevideos.domain.Error
import com.example.imagevideos.usecases.FindImagesByIdUseCase
import com.example.imagevideos.usecases.SwitchImageFavoriteUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class VideoViewModel(
    private val imageId: Int,
    private val findImagesByIdUseCase: FindImagesByIdUseCase,
    private val switchImageFavoriteUseCase: SwitchImageFavoriteUseCase
): ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            findImagesByIdUseCase(imageId)
                .catch { cause -> _state.update { it.copy(error = cause.toError()) } }
                .collect{ image -> _state.update { UiState(image = image) }}
            }
    }


    fun onFavoriteClicked() {
        viewModelScope.launch {
            _state.value.image?.let {
                val error = switchImageFavoriteUseCase(it)
            _state.update{it.copy(error= error)}

        }
        }
    }

    data class UiState(val image: com.example.imagevideos.domain.Image? = null, val error: Error? = null)
}

@Suppress("UNCHECKED_CAST")
class VideoViewModelFactory(
    private val imageId: Int,
    private val findImagesByIdUseCase: FindImagesByIdUseCase,
    private val switchImageFavoriteUseCase: SwitchImageFavoriteUseCase

): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VideoViewModel(imageId, findImagesByIdUseCase, switchImageFavoriteUseCase) as T
    }
}