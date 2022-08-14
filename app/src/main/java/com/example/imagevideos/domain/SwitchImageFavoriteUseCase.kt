package com.example.imagevideos.domain

import com.example.imagevideos.model.Error
import com.example.imagevideos.model.database.Image
import com.example.imagevideos.model.repositories.ImagesRepository

class SwitchImageFavoriteUseCase(private val imagesRepository: ImagesRepository) {

    suspend operator fun invoke(image: Image): Error? = imagesRepository.switchFavorite(image)
}