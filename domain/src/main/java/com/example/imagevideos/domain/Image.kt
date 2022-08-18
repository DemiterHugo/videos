package com.example.imagevideos.domain

data class Image(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val views: Int,
    val downloads: Int,
    val likes: Int,
    val comments: Int,
    val favorite: Boolean
)