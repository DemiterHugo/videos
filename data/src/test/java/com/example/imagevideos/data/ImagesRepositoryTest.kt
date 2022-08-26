package com.example.imagevideos.data

import arrow.core.right
import com.example.imagevideos.data.datasource.LocalImageDataSource
import com.example.imagevideos.data.datasource.RemoteImageDataSource
import com.example.imagevideos.data.repositories.ImagesRepository
import com.example.imagevideos.data.repositories.RegionRepository
import com.example.imagevideos.domain.Image
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.argThat
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class ImagesRepositoryTest {

    @Mock
    lateinit var localImageDataSource: LocalImageDataSource

    @Mock
    lateinit var remoteImageDataSource: RemoteImageDataSource

    @Mock
    lateinit var regionRepository: RegionRepository

    lateinit var imagesRepository: ImagesRepository

    private val localImages = flowOf(listOf(sampleImage.copy(5)))


    @Before
    fun setUp(){
        whenever(localImageDataSource.images).thenReturn(localImages)
        imagesRepository = ImagesRepository(regionRepository, localImageDataSource, remoteImageDataSource)
    }

    @Test
    fun `Get images are taken from local data source if available`(): Unit = runBlocking{

        val result = imagesRepository.getImages

        assertEquals(localImages, result)
    }

    @Test
    fun `Find image by id is done in local data source`(): Unit = runBlocking {
        val localImage = flowOf(sampleImage.copy(id = 5))
        whenever(localImageDataSource.findImageById(5)).thenReturn(localImage)

        val result = imagesRepository.findImageById(5)

        assertEquals(localImage,result)
    }

    @Test
    fun `Switching favorite updates local data source`(): Unit = runBlocking {
        val image = sampleImage.copy(id = 2)

        imagesRepository.switchFavorite(image)

        verify(localImageDataSource).save(argThat{ get(0).id == 2})
    }

    @Test
    fun `Switching favorite mark as unfavorite a favorite image`(): Unit = runBlocking {
        val image = sampleImage.copy(favorite = true)

        imagesRepository.switchFavorite(image)

        verify(localImageDataSource).save(argThat{!get(0).favorite})

    }

    @Test
    fun `Images are saved to local data source when it's empty`(): Unit = runBlocking {
        val remoteImages = listOf(sampleImage.copy(id = 2))
        whenever(localImageDataSource.isEmpty()).thenReturn(true)
        whenever(regionRepository.findLastRegion()).thenReturn("en")
        whenever(remoteImageDataSource.findImages(any())).thenReturn(remoteImages.right())

        imagesRepository.requestImages()

        verify(localImageDataSource).save(remoteImages)
    }
}

private val sampleImage = Image(
    id = 0,
    imageUrl = "url",
    title = "Demiter",
    views = 12345,
    downloads = 1200,
    likes = 2100, comments = 120,
    favorite = false
)