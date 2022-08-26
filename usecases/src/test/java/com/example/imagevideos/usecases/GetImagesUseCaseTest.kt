package com.example.imagevideos.usecases

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class GetImagesUseCaseTest{

    @Test
    fun `Invoke calls images repositoriy`(): Unit = runBlocking {
        val images = flowOf(listOf(sampleImage.copy(id = 3)))
        val getImagesUseCase = GetImagesUseCase(mock(){
            on { getImages } doReturn (images)
        })

        val result = getImagesUseCase()

        assertEquals(images,result)
    }
}