package com.example.imagevideos.data.database

import com.example.imagevideos.data.datasource.LocalVideoDataSource
import kotlinx.coroutines.flow.Flow
import com.example.imagevideos.domain.Video

class RoomVideoDataSource(private val videoDao: VideoDao) : LocalVideoDataSource {

    override val videos: Flow<List<Video>> = videoDao.getVideos()

    override fun isEmpty(): Boolean = videoDao.videoCount() == 0

    override fun save(videos: List<Video>) {
        videoDao.insertVideos(videos)
    }
}