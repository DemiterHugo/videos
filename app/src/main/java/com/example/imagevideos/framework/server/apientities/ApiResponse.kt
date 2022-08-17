package com.example.imagevideos.framework.server.apientities

data class ApiResponse<T>(
    val hits: List<T>,
    val total: Int,
    val totalHits: Int
)