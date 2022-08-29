package com.example.imagevideos.ui.main

import app.cash.turbine.test
import com.example.imagevideos.CoroutinesTestRule
import com.example.imagevideos.data.repositories.ImagesRepository
import com.example.imagevideos.data.repositories.RegionRepository
import com.example.imagevideos.domain.Image
import com.example.imagevideos.ui.*
import com.example.imagevideos.usecases.GetImagesUseCase
import com.example.imagevideos.usecases.RequestImagesUseCase
import com.example.testshared.sampleImage
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainIntegrationTests {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `Data is loaded from server when local source is empty`() = runTest {
        val remoteData = listOf(sampleImage.copy(1), sampleImage.copy(2))
        val vm = buildViewModelWith(localData = emptyList(), remoteData = remoteData)

        vm.onUiReady()

        vm.state.test {
            Assert.assertEquals(MainViewModel.UiState(),awaitItem())
            Assert.assertEquals(MainViewModel.UiState(images = emptyList()), awaitItem())
            Assert.assertEquals(MainViewModel.UiState(images = remoteData, loading = false),awaitItem())
            cancel()
        }
    }

    @Test
    fun `data is loaded from local source when available`() = runTest {
        val localData = listOf(sampleImage.copy(10), sampleImage.copy(11))
        val remoteData = listOf(sampleImage.copy(4), sampleImage.copy(5))
        val vm = buildViewModelWith(localData, remoteData)

        vm.state.test {
            Assert.assertEquals(MainViewModel.UiState(), awaitItem())
            Assert.assertEquals(MainViewModel.UiState(images = localData),awaitItem())
            cancel()
        }
    }

    fun buildViewModelWith(
        localData: List<Image> = emptyList(),
        remoteData: List<Image> = emptyList()
    ): MainViewModel{
        val imagesRepository = buildRepositoryWith(localData, remoteData)
        val getImagesUseCase = GetImagesUseCase(imagesRepository)
        val requestImagesUseCase = RequestImagesUseCase(imagesRepository)
        return MainViewModel(getImagesUseCase, requestImagesUseCase)
    }
}