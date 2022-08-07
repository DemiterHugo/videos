package com.example.imagevideos.model.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {

    @Query("SELECT * FROM Image")
    fun getImages(): Flow<List<Image>>

    @Query("SELECT * FROM Image WHERE id = :id")
    fun findImageById(id: Int): Flow<Image>

    @Query("SELECT COUNT(id) FROM Image")
    fun imageCount():Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImages(images: List<Image>)

    @Update
    fun updateImage(image: Image)
}