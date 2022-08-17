package com.example.imagevideos.framework.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {

    @Query("SELECT * FROM Image")
    fun getImages(): Flow<List<Image>>

    @Query("SELECT * FROM Image WHERE id = :id")
    fun findImageById(id: Int): Flow<Image>

    @Query("SELECT COUNT(id) FROM Image")
    suspend fun imageCount():Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImages(images: List<Image>)

}