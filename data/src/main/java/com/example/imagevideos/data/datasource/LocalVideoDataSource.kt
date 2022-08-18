package com.example.imagevideos.data.datasource


import com.example.imagevideos.domain.Video
import kotlinx.coroutines.flow.Flow

interface LocalVideoDataSource {
    val videos: Flow<List<Video>>

    fun isEmpty(): Boolean

    fun save(videos: List<Video>)
}

