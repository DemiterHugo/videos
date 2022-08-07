package com.example.imagevideos.model.repositories

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.example.imagevideos.R
import com.example.imagevideos.model.network.ApiClient
import com.example.imagevideos.model.network.apientities.ApiImage
import com.example.imagevideos.model.network.apientities.ApiResponse

class ImagesRepository(application: Application) {

    private val apiKey = application.getString(R.string.api_key)
    private val imageType = application.getString(R.string.image_type)
    private val editor = true
    private val regionRepository = RegionRepository(application)

    suspend fun findImages(): ApiResponse<ApiImage> {
        return ApiClient.imageService.listImages(
            apiKey,
            imageType,
            editor,
            regionRepository.findLastRegion()
        )
    }
}