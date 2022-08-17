package com.example.imagevideos.data.repositories

import com.example.imagevideos.data.datasource.LocalImageDataSource
import com.example.imagevideos.data.datasource.RemoteImageDataSource
import com.example.imagevideos.domain.Error
import com.example.imagevideos.domain.Image
import com.example.imagevideos.domain.tryCall
import kotlinx.coroutines.flow.Flow

class ImagesRepository(
    private val regionRepository: RegionRepository,
    private val localImageDataSource: LocalImageDataSource,
    private val remoteImageDataSource: RemoteImageDataSource
) {

    val getImages = localImageDataSource.images

    fun findImageById(id: Int): Flow<Image> {
        return localImageDataSource.findImageById(id)
    }

    suspend fun requestImages(): Error? = tryCall{
        if (localImageDataSource.isEmpty()){
            val images = remoteImageDataSource.findImages(regionRepository.findLastRegion())
            localImageDataSource.save(images)
        }
    }

    suspend fun switchFavorite(image: Image): Error? = tryCall {
        val updateImage = image.copy(favorite = !image.favorite)
        localImageDataSource.save(listOf(updateImage))
    }
}

/*
ServerImageDataSource(
apiKey = application.getString(R.string.api_key),
imageType = application.getString(R.string.image_type),
editor = true, */