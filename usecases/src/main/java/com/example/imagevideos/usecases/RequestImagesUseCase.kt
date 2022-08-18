package com.example.imagevideos.usecases

import com.example.imagevideos.data.repositories.ImagesRepository
import com.example.imagevideos.domain.Error

class RequestImagesUseCase(private val imagesRepository: ImagesRepository) {

    suspend operator fun invoke(): Error? = imagesRepository.requestImages()
}