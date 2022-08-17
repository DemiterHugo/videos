package com.example.imagevideos.data.datasource

import com.example.imagevideos.domain.Image

interface RemoteImageDataSource {
    suspend fun findImages(region: String): List<Image>
}