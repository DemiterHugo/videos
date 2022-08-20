package com.example.imagevideos.usecases

import com.example.imagevideos.data.repositories.ImagesRepository
import com.example.imagevideos.domain.Error
import com.example.imagevideos.domain.Image
import javax.inject.Inject

class SwitchImageFavoriteUseCase @Inject constructor(private val imagesRepository: ImagesRepository) {

    suspend operator fun invoke(image: Image): Error? = imagesRepository.switchFavorite(image)

}