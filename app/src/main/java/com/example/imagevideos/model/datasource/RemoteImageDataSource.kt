package com.example.imagevideos.model.datasource

import com.example.imagevideos.model.network.ApiClient
import com.example.imagevideos.model.network.apientities.ApiImage
import com.example.imagevideos.model.network.apientities.ApiResponse
import com.example.imagevideos.model.repositories.RegionRepository

class RemoteImageDataSource(
    private val apiKey: String,
    private val imageType: String,
    private val editor: Boolean,
    //private val regionRepository: RegionRepository
){
    suspend fun findImages(region: String): ApiResponse<ApiImage> {
        return ApiClient.imageService.listImages(
            apiKey,
            imageType,
            editor,
            region
            //regionRepository.findLastRegion()
        )
    }
}