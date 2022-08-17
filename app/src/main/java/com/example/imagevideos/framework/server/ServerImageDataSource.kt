package com.example.imagevideos.framework.server

import com.example.imagevideos.data.datasource.RemoteImageDataSource
import com.example.imagevideos.framework.server.ApiClient
import com.example.imagevideos.framework.server.apientities.ApiImage
import com.example.imagevideos.domain.Image



class ServerImageDataSource(
    private val apiKey: String,
    private val imageType: String,
    private val editor: Boolean
) : RemoteImageDataSource {
    override suspend fun findImages(region: String): List<Image> =
        ApiClient.imageService.listImages(
            apiKey,
            imageType,
            editor,
            region
        ).hits.toDomainImage()

}
private fun List<ApiImage>.toDomainImage(): List<Image> = map{it.toDomainImage()}
private fun ApiImage.toDomainImage(): Image = Image(
    id = id,
    imageUrl = webformatURL,
    title = tags,
    views = views,
    downloads = downloads,
    likes = likes,
    comments = comments,
    favorite = false
)