package com.example.imagevideos.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Image(
    @PrimaryKey(autoGenerate = true)val id: Int,
    val imageUrl: String,
    val title: String,
    val views: Int,
    val downloads: Int,
    val likes: Int,
    val comments: Int,
    val favorite: Boolean
)
