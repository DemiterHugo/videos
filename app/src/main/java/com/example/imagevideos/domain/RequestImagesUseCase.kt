package com.example.imagevideos.domain

import com.example.imagevideos.model.Error
import com.example.imagevideos.model.repositories.ImagesRepository

class RequestImagesUseCase(private val imagesRepository: ImagesRepository) {

    suspend operator fun invoke(): Error? = imagesRepository.requestImages()
}