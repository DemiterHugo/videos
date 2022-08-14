package com.example.imagevideos.domain

import com.example.imagevideos.model.database.Image
import com.example.imagevideos.model.repositories.ImagesRepository
import kotlinx.coroutines.flow.Flow

class FindImagesByIdUseCase(private val imagesRepository: ImagesRepository) {

    operator fun invoke(id: Int): Flow<Image> = imagesRepository.findImageById(id)
}