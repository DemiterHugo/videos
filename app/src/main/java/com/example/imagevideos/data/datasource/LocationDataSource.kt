package com.example.imagevideos.data.datasource

import android.location.Location


interface LocationDataSource {
    suspend fun findLastLocation(): Location?
}

