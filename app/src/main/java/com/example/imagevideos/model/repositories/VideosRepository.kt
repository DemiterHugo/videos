package com.example.imagevideos.model.repositories

import android.app.Application
import com.example.imagevideos.R
import com.example.imagevideos.model.network.ApiClient
import com.example.imagevideos.model.network.apientities.ApiImage
import com.example.imagevideos.model.network.apientities.ApiResponse
import com.example.imagevideos.model.network.apientities.ApiVideo

class VideosRepository(application: Application) {

    private val apiKey = application.getString(R.string.api_key)
    private val editor = true


    suspend fun findRelatedVideos(image:ApiImage): ApiResponse<ApiVideo> {

        return ApiClient.videoService.listVideos(
            apiKey,
            image.tags.replace( ',',   '+').replace("\\s".toRegex(), replacement = ""),
            editor
        )
    }
}