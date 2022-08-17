package com.example.imagevideos.data.repositories

import com.example.imagevideos.App
import com.example.imagevideos.framework.database.Video
import com.example.imagevideos.framework.server.apientities.ApiVideo

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