package com.example.imagevideos.data.server

import com.example.imagevideos.data.server.apientities.ApiResponse
import com.example.imagevideos.data.server.apientities.ApiVideo
import retrofit2.http.GET
import retrofit2.http.Query

interface VideoService {

    @GET("api/videos/?")
    suspend fun listVideos(
        @Query("key") apiKey: String,
        @Query("q") q: String,
        @Query("editors_choice") editor: Boolean
    ): ApiResponse<ApiVideo>
}