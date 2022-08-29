package com.example.imagevideos.ui.detail

import app.cash.turbine.test
import com.example.imagevideos.CoroutinesTestRule
import com.example.imagevideos.data.repositories.ImagesRepository
import com.example.imagevideos.domain.Image
import com.example.imagevideos.ui.buildRepositoryWith
import com.example.imagevideos.usecases.FindImagesByIdUseCase
import com.example.imagevideos.usecases.SwitchImageFavoriteUseCase
import com.example.testshared.sampleImage
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class VideoIntegrationTests {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `UI is updated with the image on start`() = runTest {
        val vm = buildViewModelWith(2, localData = listOf(sampleImage.copy(1), sampleImage.copy(2)))

        vm.state.test {
            Assert.assertEquals(VideoViewModel.UiState(),awaitItem())
            Assert.assertEquals(VideoViewModel.UiState(image = sampleImage.copy(2)),awaitItem())
            cancel()
        }
    }

    @Test
    fun `Favorite is updated in local data source`() = runTest {
        val vm = buildViewModelWith(2, localData = listOf(sampleImage.copy(1), sampleImage.copy(2)))

        vm.onFavoriteClicked()

        vm.state.test {
            Assert.assertEquals(VideoViewModel.UiState(), awaitItem())
            Assert.assertEquals(VideoViewModel.UiState(image = sampleImage.copy(id = 2, favorite = false)), awaitItem())
            Assert.assertEquals(VideoViewModel.UiState(image = sampleImage.copy(id = 2, favorite = true)), awaitItem())
            cancel()
        }
    }

    fun buildViewModelWith(
        id: Int,
        localData: List<Image> = emptyList(),
        remoteData: List<Image> = emptyList()
    ): VideoViewModel{
        val imagesRepository = buildRepositoryWith(localData, remoteData)
        val switchImageFavoriteUseCase = SwitchImageFavoriteUseCase(imagesRepository)
        val findImageByIdUseCase = FindImagesByIdUseCase(imagesRepository)
        return VideoViewModel(id, findImageByIdUseCase, switchImageFavoriteUseCase)
    }
}