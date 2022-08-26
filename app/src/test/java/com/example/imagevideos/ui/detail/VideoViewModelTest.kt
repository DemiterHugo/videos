package com.example.imagevideos.ui.detail

import app.cash.turbine.test
import com.example.imagevideos.CoroutinesTestRule
import com.example.imagevideos.usecases.FindImagesByIdUseCase
import com.example.imagevideos.usecases.SwitchImageFavoriteUseCase
import com.example.testshared.sampleImage
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class VideoViewModelTest{

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Mock
    lateinit var findImagesByIdUseCase: FindImagesByIdUseCase

    @Mock
    lateinit var switchImageFavoriteUseCase: SwitchImageFavoriteUseCase

    private lateinit var vm: VideoViewModel

    private val image = sampleImage.copy(id = 5)

    @Before
    fun setup(){
        whenever(findImagesByIdUseCase(id = 5)).thenReturn(flowOf(image))
        vm = VideoViewModel(5,findImagesByIdUseCase,switchImageFavoriteUseCase)
    }

    @Test
    fun `UI is updated with the image on start`() = runTest {
        vm.state.test {
            assertEquals(VideoViewModel.UiState(),awaitItem())
            assertEquals(VideoViewModel.UiState(image),awaitItem())
            cancel()
        }
    }

    @Test
    fun `Favorite action calls the corresponding use case`() = runTest {
        vm.onFavoriteClicked()
        runCurrent()

        verify(switchImageFavoriteUseCase).invoke(image)
    }


}