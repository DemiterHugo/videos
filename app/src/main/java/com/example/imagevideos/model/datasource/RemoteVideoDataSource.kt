package com.example.imagevideos.model.datasource

import com.example.imagevideos.model.database.Image
import com.example.imagevideos.model.network.ApiClient
import com.example.imagevideos.model.network.apientities.ApiResponse
import com.example.imagevideos.model.network.apientities.ApiVideo

class RemoteVideoDataSource(
    private val apiKey: String,
    private val editor: Boolean
){
    suspend fun findRelatedVideos(image: Image): ApiResponse<ApiVideo> {
        return ApiClient.videoService.listVideos(
            apiKey,
            image.title.replace( ',',   '+').replace("\\s".toRegex(), replacement = ""),
            editor
        )
    }
}