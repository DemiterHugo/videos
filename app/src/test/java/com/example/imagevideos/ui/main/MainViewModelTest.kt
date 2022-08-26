package com.example.imagevideos.ui.main

import androidx.lifecycle.ViewModel
import app.cash.turbine.test
import com.example.imagevideos.CoroutinesTestRule
import com.example.imagevideos.usecases.GetImagesUseCase
import com.example.imagevideos.usecases.RequestImagesUseCase
import com.example.testshared.sampleImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest{

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var getImagesUseCase: GetImagesUseCase

    @Mock
    lateinit var requestImagesUseCase: RequestImagesUseCase

    private lateinit var vm: MainViewModel

    private var images = listOf(sampleImage.copy(id = 4))


    @Before
    fun setUp(){
        whenever(getImagesUseCase()).thenReturn(flowOf(images))
        vm = MainViewModel(getImagesUseCase,requestImagesUseCase)
    }

    @Test
    fun `State is update with current cached content immediately`() = runTest {

        vm.state.test {
            assertEquals(MainViewModel.UiState(), awaitItem())
            assertEquals(MainViewModel.UiState(images= images),awaitItem())
            cancel()
        }
    }

    @Test
    fun `Progress is shown when screen start and hidden when it finishes requesting movies`() = runTest {

        vm.onUiReady()

        vm.state.test {
            assertEquals(MainViewModel.UiState(), awaitItem())
            assertEquals(MainViewModel.UiState(images= images),awaitItem())
            assertEquals(MainViewModel.UiState(images= images, loading = true), awaitItem())
            assertEquals(MainViewModel.UiState(images= images, loading = false),awaitItem())
            cancel()
        }
    }
}