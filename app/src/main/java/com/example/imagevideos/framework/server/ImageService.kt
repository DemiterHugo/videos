package com.example.imagevideos.framework.server

import com.example.imagevideos.framework.server.apientities.ApiImage
import com.example.imagevideos.framework.server.apientities.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageService {

    @GET("api/")
    suspend fun listImages(
        @Query("key") apiKey: String,
        @Query("image_type") imageType: String,
        @Query("editors_choice") editor: Boolean,
        @Query("lang") lang: String
    ): ApiResponse<ApiImage>
}