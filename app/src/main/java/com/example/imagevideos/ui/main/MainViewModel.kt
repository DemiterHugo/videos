package com.example.imagevideos.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.imagevideos.usecases.GetImagesUseCase
import com.example.imagevideos.usecases.RequestImagesUseCase
import com.example.imagevideos.domain.Image
import com.example.imagevideos.domain.toError
import com.example.imagevideos.domain.Error

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val getImagesUseCase: GetImagesUseCase,
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
            _state.value = UiState(loading = true)
            val error = requestImagesUseCase()
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
class MainViewModelFactory(
    private val getImagesUseCase: GetImagesUseCase,
    private val requestImagesUseCase: RequestImagesUseCase
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(getImagesUseCase, requestImagesUseCase) as T
    }
}


