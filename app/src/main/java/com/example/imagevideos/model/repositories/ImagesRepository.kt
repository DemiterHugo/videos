package com.example.imagevideos.model.repositories

import com.example.imagevideos.App
import com.example.imagevideos.R
import com.example.imagevideos.model.database.Image
import com.example.imagevideos.model.datasource.LocalImageDataSource
import com.example.imagevideos.model.datasource.RemoteImageDataSource
import com.example.imagevideos.model.network.apientities.ApiImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ImagesRepository(application: App) {

    private val regionRepository = RegionRepository(application)
    private val localImageDataSource = LocalImageDataSource(application.db.imageDao())
    private val remoteImageDataSource = RemoteImageDataSource(
        apiKey = application.getString(R.string.api_key),
        imageType = application.getString(R.string.image_type),
        editor = true,
    )

    val getImages = localImageDataSource.images

    fun findImageById(id: Int): Flow<Image> {
        return localImageDataSource.findImageById(id)
    }

    suspend fun requestImages(){
        if (localImageDataSource.isEmpty()){
            val images = remoteImageDataSource.findImages(regionRepository.findLastRegion())
            localImageDataSource.save(images.hits.map { it.toLocalImage() })
        }
    }

    suspend fun switchFavorite(image: Image) {
        val updateImage = image.copy(favorite = !image.favorite)
        localImageDataSource.save(listOf(updateImage))
    }
}





private fun ApiImage.toLocalImage(): Image = Image(
    id = id,
    imageUrl = webformatURL,
    title = tags,
    views = views,
    downloads = downloads,
    likes = likes,
    comments = comments,
    favorite = false
)