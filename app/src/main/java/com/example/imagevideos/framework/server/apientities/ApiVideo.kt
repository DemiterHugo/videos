package com.example.imagevideos.framework.server.apientities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ApiVideo(
    val comments: Int,
    val downloads: Int,
    val duration: Int,
    val id: Int,
    val likes: Int,
    val pageURL: String,
    val picture_id: String,
    val tags: String,
    val type: String,
    val user: String,
    val userImageURL: String,
    val user_id: Int,
    val videos: Videos,
    val views: Int
):Parcelable