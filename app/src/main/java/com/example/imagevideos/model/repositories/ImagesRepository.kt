package com.example.imagevideos.model.repositories

import androidx.appcompat.app.AppCompatActivity
import com.example.imagevideos.R
import com.example.imagevideos.model.network.ApiClient
import com.example.imagevideos.model.network.apientities.ApiImage
import com.example.imagevideos.model.network.apientities.ApiResponse

class ImagesRepository(activity: AppCompatActivity) {

    private val apiKey = activity.getString(R.string.api_key)
    private val imageType = activity.getString(R.string.image_type)
    private val editor = true

    suspend fun findImages(): ApiResponse<ApiImage> {
        return ApiClient.imageService.listImages(apiKey,imageType,editor)
    }
}