package com.example.imagevideos.data.repositories

import com.example.imagevideos.data.datasource.LocationDataSource
import com.example.imagevideos.data.repositories.PermissionChecker.Permission.COARSE_LOCATION
import javax.inject.Inject


class RegionRepository @Inject constructor(
    private val locationDataSource: LocationDataSource,
    private val permissionChecker: PermissionChecker
) {

    companion object{
        private const val DEFAULT_REGION = "en"
    }

    suspend fun findLastRegion(): String {
        return if (permissionChecker.check(COARSE_LOCATION)) {
            locationDataSource.findLastLocation() ?: DEFAULT_REGION
        } else {
            DEFAULT_REGION
        }
    }
}