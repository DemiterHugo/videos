package com.example.imagevideos.data.server

import arrow.core.Either
import com.example.imagevideos.data.datasource.RemoteImageDataSource
import com.example.imagevideos.data.server.apientities.ApiImage
import com.example.imagevideos.data.tryCall
import com.example.imagevideos.di.ApiKey
import com.example.imagevideos.domain.Error
import com.example.imagevideos.domain.Image
import javax.inject.Inject


class ServerImageDataSource @Inject constructor(
    @ApiKey private val apiKey: String,
      //val imageType: String,
      //val editor: Boolean = true
) : RemoteImageDataSource {

    override suspend fun findImages(region: String): Either<Error,List<Image>> = tryCall{
        ApiClient.imageService.listImages(
            apiKey,
            //imageType,
            //editor,
            region
        ).hits.toDomainImage()
    }


}

private fun List<ApiImage>.toDomainImage(): List<Image> = map{it.toDomainImage()}
private fun ApiImage.toDomainImage(): Image =
    Image(
        id = id,
        imageUrl = webformatURL,
        title = tags,
        views = views,
        downloads = downloads,
        likes = likes,
        comments = comments,
        favorite = false
    )