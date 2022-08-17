package com.example.imagevideos.usecases

import com.example.imagevideos.data.repositories.ImagesRepository
import com.example.imagevideos.domain.Error
import com.example.imagevideos.domain.Image

class SwitchImageFavoriteUseCase(private val imagesRepository: ImagesRepository) {

    suspend operator fun invoke(image: Image): Error? = imagesRepository.switchFavorite(image)

}