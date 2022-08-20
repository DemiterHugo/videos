package com.example.imagevideos.data.repositories

import com.example.imagevideos.data.datasource.LocalImageDataSource
import com.example.imagevideos.data.datasource.RemoteImageDataSource
import com.example.imagevideos.domain.Error
import com.example.imagevideos.domain.Image
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImagesRepository @Inject constructor(
    private val regionRepository: RegionRepository,
    private val localImageDataSource: LocalImageDataSource,
    private val remoteImageDataSource: RemoteImageDataSource
) {

    val getImages = localImageDataSource.images

    fun findImageById(id: Int): Flow<Image> = localImageDataSource.findImageById(id)

    suspend fun requestImages(): Error?{
        if (localImageDataSource.isEmpty()){
            val images = remoteImageDataSource.findImages(regionRepository.findLastRegion())
            images.fold(ifLeft = { return it}){
                localImageDataSource.save(it)
            }
        }
        return null
    }

    suspend fun switchFavorite(image: Image): Error? {
        val updateImage = image.copy(favorite = !image.favorite)
        return localImageDataSource.save(listOf(updateImage))
    }
}
