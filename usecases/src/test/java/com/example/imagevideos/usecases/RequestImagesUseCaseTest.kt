package com.example.imagevideos.usecases

import com.example.imagevideos.data.repositories.ImagesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class RequestImagesUseCaseTest{

    @Test
    fun `Invoke calls images repository`(): Unit = runBlocking {
        val imagesRepository = mock<ImagesRepository>()
        val requestImagesUseCase = RequestImagesUseCase(imagesRepository)

        requestImagesUseCase()

        verify(imagesRepository).requestImages()
    }
}