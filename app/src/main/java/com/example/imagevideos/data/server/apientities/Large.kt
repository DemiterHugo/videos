package com.example.imagevideos.data.server.apientities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Large(
    val height: Int,
    val size: Int,
    val url: String,
    val width: Int
):Parcelable