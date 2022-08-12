package com.example.imagevideos.model.repositories

import com.example.imagevideos.App
import com.example.imagevideos.R
import com.example.imagevideos.model.database.Image
import com.example.imagevideos.model.database.Video
import com.example.imagevideos.model.datasource.LocalVideoDataSource
import com.example.imagevideos.model.datasource.RemoteVideoDataSource
import com.example.imagevideos.model.network.apientities.ApiVideo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VideosRepository( application: App) {

    /**private val localVideoDataSource = LocalVideoDataSource(application.db.videoDao())
    private val remoteVideoDataSource = RemoteVideoDataSource(
        apiKey = application.getString(R.string.api_key),
        editor = true
    )

    val getVideos =localVideoDataSource.videos

    suspend fun requestVideos(image: Image) = withContext(Dispatchers.IO){
        if(localVideoDataSource.isEmpty()){
            val videos = remoteVideoDataSource.findRelatedVideos(image)
            localVideoDataSource.save(videos = videos.hits.map { it.toLocalVideo() })
        }
    } **/
}




private fun ApiVideo.toLocalVideo(): Video = Video(
    id = id,
    videoUrl = videos.tiny.url,
    title = tags
)