package com.example.imagevideos.data.datasource

import com.example.imagevideos.framework.database.Video
import com.example.imagevideos.framework.database.VideoDao
import kotlinx.coroutines.flow.Flow

class LocalVideoDataSource(private val videoDao: VideoDao){

    val videos: Flow<List<Video>> = videoDao.getVideos()

    fun isEmpty(): Boolean = videoDao.videoCount() == 0
    fun save(videos: List<Video>) {
        videoDao.insertVideos(videos)
    }
}