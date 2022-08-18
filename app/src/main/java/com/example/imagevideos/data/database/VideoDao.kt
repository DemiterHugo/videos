package com.example.imagevideos.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.example.imagevideos.domain.Video

@Dao
interface VideoDao {

    @Query("SELECT * FROM VIDEO")
    fun getVideos(): Flow<List<Video>>

    @Query("SELECT COUNT(id) FROM VIDEO")
    fun videoCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVideos(videos: List<Video>)

}