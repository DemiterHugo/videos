package com.example.imagevideos.data.datasource

import arrow.core.Either
import com.example.imagevideos.domain.Error
import com.example.imagevideos.domain.Image

interface RemoteImageDataSource {
    suspend fun findImages(region: String): Either<Error, List<Image>>
}