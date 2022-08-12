package com.example.imagevideos.model.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
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
): Parcelable
