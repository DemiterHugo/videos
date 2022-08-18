package com.example.imagevideos.data.server

import arrow.core.Either
import com.example.imagevideos.data.datasource.RemoteVideoDataSource
import com.example.imagevideos.data.server.apientities.ApiVideo
import com.example.imagevideos.data.tryCall
import com.example.imagevideos.domain.Error
import com.example.imagevideos.domain.Image
import com.example.imagevideos.domain.Video



class ServerVideoDataSource(
    private val apiKey: String,
    private val editor: Boolean
) : RemoteVideoDataSource {

    override suspend fun findVideos(image: Image): Either<Error,List<Video>> = tryCall {
        ApiClient.videoService.listVideos(
            apiKey,
            image.title.replace( ',',   '+').replace("\\s".toRegex(), replacement = ""),
            editor
        ).hits.toDomainVideo()
    }
}

private fun List<ApiVideo>.toDomainVideo(): List<Video> = map { it.toDomainVideo() }
private fun ApiVideo.toDomainVideo(): Video =
    Video(
        id = id,
        videoUrl = videos.small.toString(),
        title = tags
    )