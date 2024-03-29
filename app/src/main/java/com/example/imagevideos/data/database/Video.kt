package com.example.imagevideos.data.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
data class Video(
    @PrimaryKey(autoGenerate = true)val id: Int,
    val videoUrl: String,
    val title: String
)