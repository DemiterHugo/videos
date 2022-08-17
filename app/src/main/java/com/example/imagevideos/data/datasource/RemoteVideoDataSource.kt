package com.example.imagevideos.data.datasource

import com.example.imagevideos.framework.database.Image
import com.example.imagevideos.framework.server.ApiClient
import com.example.imagevideos.framework.server.apientities.ApiResponse
import com.example.imagevideos.framework.server.apientities.ApiVideo

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