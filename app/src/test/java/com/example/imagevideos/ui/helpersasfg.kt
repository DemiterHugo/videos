package com.example.imagevideos.ui

import com.example.imagevideos.data.repositories.ImagesRepository
import com.example.imagevideos.data.repositories.RegionRepository
import com.example.imagevideos.domain.Image

fun buildRepositoryWith(localData: List<Image>, remoteData: List<Image>): ImagesRepository{

    val localImageDataSource = FakeLocalImageDataSource().apply { inMemoryImages.value = localData  }
    val remoteImageDataSource = FakeRemoteImageDataSource().apply { images = remoteData }
    val permissionChecker = FakePermissionChecker()
    val locationDataSource = FakeLocationDataSource()
    val regionRepository = RegionRepository(locationDataSource, permissionChecker)
    return ImagesRepository(regionRepository, localImageDataSource,remoteImageDataSource)
}