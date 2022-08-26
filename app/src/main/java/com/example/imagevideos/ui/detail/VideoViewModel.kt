package com.example.imagevideos.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagevideos.data.toError
import com.example.imagevideos.di.ImageId
import com.example.imagevideos.domain.Error
import com.example.imagevideos.domain.Image
import com.example.imagevideos.usecases.FindImagesByIdUseCase
import com.example.imagevideos.usecases.SwitchImageFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    @ImageId private val imageId: Int,
    findImagesByIdUseCase: FindImagesByIdUseCase,
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

    data class UiState(val image: Image? = null, val error: Error? = null)
}
