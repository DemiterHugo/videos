package com.example.imagevideos.usecases

import com.example.imagevideos.data.repositories.ImagesRepository
import com.example.imagevideos.domain.Image
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FindImagesByIdUseCase @Inject constructor(private val imagesRepository: ImagesRepository) {

    operator fun invoke(id: Int): Flow<Image> = imagesRepository.findImageById(id)
}