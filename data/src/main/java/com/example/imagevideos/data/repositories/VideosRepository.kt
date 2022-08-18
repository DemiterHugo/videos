package com.example.imagevideos.data.repositories

import com.example.imagevideos.data.datasource.LocalVideoDataSource
import com.example.imagevideos.data.datasource.RemoteVideoDataSource
import com.example.imagevideos.domain.Error
import com.example.imagevideos.domain.Image
import com.example.imagevideos.domain.Video


class VideosRepository(
    private val localVideoDataSource: LocalVideoDataSource,
    private val remoteVideoDataSource: RemoteVideoDataSource,
    private val image: Image
) {

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
    val getVideos = localVideoDataSource.videos

    suspend fun requestVideos(): Error?{
        if (localVideoDataSource.isEmpty()){
            val videos = remoteVideoDataSource.findVideos(image)
            videos.fold(ifLeft = {return it}){
                localVideoDataSource.save(it)
            }
        }
        return null
    }
}

