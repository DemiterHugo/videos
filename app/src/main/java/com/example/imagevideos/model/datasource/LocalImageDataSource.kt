package com.example.imagevideos.model.datasource

import com.example.imagevideos.model.database.Image
import com.example.imagevideos.model.database.ImageDao
import kotlinx.coroutines.flow.Flow

//las fuentes de datos deben gestionar sus propios hilos
class LocalImageDataSource(private val imageDao: ImageDao){

    val images: Flow<List<Image>> = imageDao.getImages()

    suspend fun isEmpty(): Boolean = imageDao.imageCount() == 0

    fun findImageById(id: Int): Flow<Image> {
        return imageDao.findImageById(id)
    }

    suspend fun save(images: List<Image>){
        imageDao.insertImages(images)
    }
}