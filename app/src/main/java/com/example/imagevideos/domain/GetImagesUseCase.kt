package com.example.imagevideos.domain

import com.example.imagevideos.model.database.Image
import com.example.imagevideos.model.repositories.ImagesRepository
import kotlinx.coroutines.flow.Flow

class GetImagesUseCase(private val imagesRepository: ImagesRepository ) {

    //no necesita ser una funcion suspend xq devuelve un flow
    operator fun invoke() :Flow<List<Image>> = imagesRepository.getImages
}