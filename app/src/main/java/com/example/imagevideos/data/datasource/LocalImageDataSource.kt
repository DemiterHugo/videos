package com.example.imagevideos.data.datasource

import com.example.imagevideos.domain.Image
import kotlinx.coroutines.flow.Flow

interface LocalImageDataSource {
    val images: Flow<List<Image>>

    suspend fun isEmpty(): Boolean
    fun findImageById(id: Int): Flow<Image>

    suspend fun save(images: List<Image>)
}