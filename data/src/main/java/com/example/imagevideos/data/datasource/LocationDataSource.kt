package com.example.imagevideos.data.datasource



interface LocationDataSource {
    suspend fun findLastLocation(): String?
}

