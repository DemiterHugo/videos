package com.example.imagevideos.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.imagevideos.data.toError
import com.example.imagevideos.usecases.GetImagesUseCase
import com.example.imagevideos.usecases.RequestImagesUseCase
import com.example.imagevideos.domain.Error
import com.example.imagevideos.domain.Image
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getImagesUseCase: GetImagesUseCase,
    private val requestImagesUseCase: RequestImagesUseCase
): ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getImagesUseCase()
                .catch { cause -> _state.update {it.copy(error = cause.toError())} }
                .collect{images -> _state.update { UiState(images = images)}}
        }
    }

     fun onUiReady() {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true)
            val error = requestImagesUseCase()
            _state.update { _state.value.copy(loading = false, error = error)}
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val images: List<Image>? = null,
        val error: Error? = null
    )
}