package com.example.imagevideos.usecases

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class FindImagesByIdUseCaseTest{

    @Test
    fun `Invoke calls images repository`(): Unit = runBlocking {
        val image = flowOf(sampleImage.copy(id = 2))
        val findImagesByIdUseCase = FindImagesByIdUseCase( mock(){
            on{findImageById(id = 2)} doReturn (image)
        })

        val result = findImagesByIdUseCase(id = 2)

        assertEquals(image, result)
    }
}