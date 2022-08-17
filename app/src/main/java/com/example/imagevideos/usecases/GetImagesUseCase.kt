package com.example.imagevideos.usecases

import com.example.imagevideos.data.repositories.ImagesRepository
import com.example.imagevideos.domain.Image
import kotlinx.coroutines.flow.Flow

class GetImagesUseCase(private val imagesRepository: ImagesRepository ) {

    //no necesita ser una funcion suspend xq devuelve un flow
    operator fun invoke() :Flow<List<Image>> = imagesRepository.getImages
}