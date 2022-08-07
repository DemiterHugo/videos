package com.example.imagevideos.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Image(
    @PrimaryKey(autoGenerate = true)val id: Int,
    val imageUrl: String,
    val title: String,
    val views: String,
    val downloads: String,
    val likes: String,
    val comments: String
)
