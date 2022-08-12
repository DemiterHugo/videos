package com.example.imagevideos.model.datasource

import com.example.imagevideos.model.database.Video
import com.example.imagevideos.model.database.VideoDao
import kotlinx.coroutines.flow.Flow

class LocalVideoDataSource(private val videoDao: VideoDao){

    val videos: Flow<List<Video>> = videoDao.getVideos()

    fun isEmpty(): Boolean = videoDao.videoCount() == 0
    fun save(videos: List<Video>) {
        videoDao.insertVideos(videos)
    }
}