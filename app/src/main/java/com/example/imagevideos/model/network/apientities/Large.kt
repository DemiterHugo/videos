package com.example.imagevideos.model.network.apientities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Large(
    val height: Int,
    val size: Int,
    val url: String,
    val width: Int
):Parcelable