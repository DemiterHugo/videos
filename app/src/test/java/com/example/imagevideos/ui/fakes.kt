package com.example.imagevideos.ui

import arrow.core.Either
import arrow.core.right
import com.example.imagevideos.data.datasource.LocalImageDataSource
import com.example.imagevideos.data.datasource.LocationDataSource
import com.example.imagevideos.data.datasource.RemoteImageDataSource
import com.example.imagevideos.data.repositories.PermissionChecker
import com.example.imagevideos.domain.Error
import com.example.imagevideos.domain.Image
import com.example.testshared.sampleImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf

val defaultFakesImages = listOf(
    sampleImage.copy(id = 1),
    sampleImage.copy(id = 2),
    sampleImage.copy(id = 3),
    sampleImage.copy(id = 4)
)

class FakeLocalImageDataSource: LocalImageDataSource{

    val inMemoryImages = MutableStateFlow<List<Image>>(emptyList())

    override val images = inMemoryImages
    private lateinit var findImageFlow: MutableStateFlow<Image>



    override suspend fun isEmpty(): Boolean {
        return images.value.isEmpty()
    }

    override fun findImageById(id: Int): Flow<Image> {
        findImageFlow = MutableStateFlow(inMemoryImages.value.first { it.id == id })
        return findImageFlow
    }

    override suspend fun save(images: List<Image>): Error? {
        inMemoryImages.value = images

        if (::findImageFlow.isInitialized){
            images.firstOrNull(){ it.id == findImageFlow.value.id}
                ?.let { findImageFlow.value = it }
        }
        return null
    }
}

class FakeRemoteImageDataSource: RemoteImageDataSource{

    var images = defaultFakesImages

    override suspend fun findImages(region: String): Either<Error, List<Image>> {
        return images.right()
    }
}

class FakeLocationDataSource: LocationDataSource{

    private val location = "en"

    override suspend fun findLastLocation(): String {
        return location
    }
}

class FakePermissionChecker: PermissionChecker {

    val permissionGranted = true

    override fun check(permission: PermissionChecker.Permission): Boolean {
        return permissionGranted
    }

}