package com.example.imagevideos.model.network.apientities

data class ApiResponse<T>(
    val hits: List<T>,
    val total: Int,
    val totalHits: Int
)