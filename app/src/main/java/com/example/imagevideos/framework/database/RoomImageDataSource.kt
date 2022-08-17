package com.example.imagevideos.framework.database

import com.example.imagevideos.data.datasource.LocalImageDataSource
import com.example.imagevideos.domain.Image
import com.example.imagevideos.framework.database.Image as ImageDB
import com.example.imagevideos.framework.database.ImageDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map



//las fuentes de datos deben gestionar sus propios hilos
class RoomImageDataSource(private val imageDao: ImageDao) : LocalImageDataSource {

    override val images: Flow<List<Image>> = imageDao.getImages().map { it.toDomainImage() }

    override suspend fun isEmpty(): Boolean = imageDao.imageCount() == 0

    override fun findImageById(id: Int): Flow<Image> = imageDao.findImageById(id).map { it.toDomainImage() }


    override suspend fun save(images: List<Image>){
        imageDao.insertImages(images.toImageDB())
    }
}

private fun List<ImageDB>.toDomainImage(): List<Image> = map { it.toDomainImage() }
private fun ImageDB.toDomainImage():Image = Image(
    id,
    imageUrl,
    title,
    views,
    downloads,
    likes,
    comments,
    favorite
)
private fun List<Image>.toImageDB(): List<ImageDB> = map { it.toImageDB() }
private fun Image.toImageDB(): ImageDB = ImageDB(
    id,
    imageUrl,
    title,
    views,
    downloads,
    likes,
    comments,
    favorite
)