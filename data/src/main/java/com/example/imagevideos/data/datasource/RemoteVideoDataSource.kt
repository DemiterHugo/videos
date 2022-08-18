package com.example.imagevideos.data.datasource

import arrow.core.Either
import com.example.imagevideos.domain.Error
import com.example.imagevideos.domain.Image
import com.example.imagevideos.domain.Video

interface RemoteVideoDataSource {
    suspend fun findVideos(image: Image): Either<Error, List<Video>>
}