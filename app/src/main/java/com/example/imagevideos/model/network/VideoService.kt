package com.example.imagevideos.model.network

import com.example.imagevideos.model.network.apientities.ApiResponse
import com.example.imagevideos.model.network.apientities.ApiVideo
import retrofit2.http.GET
import retrofit2.http.Query

interface VideoService {

    @GET("api/videos/?")
    suspend fun listVideos(
        @Query("key") apiKey: String,
        @Query("q") q: String,
        //@Query("editors_choice") editor: Boolean
    ): ApiResponse<ApiVideo>
}