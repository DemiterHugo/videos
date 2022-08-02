package com.example.imagevideos.model.network

import com.example.imagevideos.model.network.apientities.ApiImage
import com.example.imagevideos.model.network.apientities.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageService {

    @GET("api/")
    suspend fun listImages(
        @Query("key") apiKey: String,
        @Query("image_type") imageType: String,
        @Query("editors_choice") editor: Boolean
    ): ApiResponse<ApiImage>
}