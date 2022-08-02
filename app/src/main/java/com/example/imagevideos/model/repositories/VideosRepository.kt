package com.example.imagevideos.model.repositories

import androidx.appcompat.app.AppCompatActivity
import com.example.imagevideos.R
import com.example.imagevideos.model.network.ApiClient
import com.example.imagevideos.model.network.apientities.ApiImage
import com.example.imagevideos.model.network.apientities.ApiResponse
import com.example.imagevideos.model.network.apientities.ApiVideo

class VideosRepository(activity: AppCompatActivity) {

    private val apiKey = activity.getString(R.string.api_key)
    private val editor = true


    suspend fun findRelatedVideos(image:ApiImage): ApiResponse<ApiVideo> {

        return ApiClient.videoService.listVideos(
            apiKey,
            //"fire+flames+grilling"
            image.tags.run {
                this.replace(oldChar = ',', newChar = '+')
                this.replace("\\s".toRegex(), replacement = "")
            }
            //editor
        )
    }
}