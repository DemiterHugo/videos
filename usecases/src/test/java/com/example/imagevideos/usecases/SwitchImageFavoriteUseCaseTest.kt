package com.example.imagevideos.usecases

import com.example.imagevideos.data.repositories.ImagesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class SwitchImageFavoriteUseCaseTest{

    @Test
    fun `Invoke calls images repository`(): Unit = runBlocking {
        val image = sampleImage.copy(id = 4)
        val imagesRepository = mock<ImagesRepository>()
        val switchImageFavoriteUseCase = SwitchImageFavoriteUseCase(imagesRepository)

        switchImageFavoriteUseCase(image)

        verify(imagesRepository).switchFavorite(image)
    }
}