package com.example.imagevideos.usecases

import com.example.imagevideos.data.repositories.ImagesRepository
import com.example.imagevideos.domain.Image
import kotlinx.coroutines.flow.Flow

class FindImagesByIdUseCase(private val imagesRepository: ImagesRepository) {

    operator fun invoke(id: Int): Flow<Image> = imagesRepository.findImageById(id)
}