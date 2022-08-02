package com.example.imagevideos.model.network.apientities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Videos(
    val large: Large,
    val medium: Medium,
    val small: Small,
    val tiny: Tiny
):Parcelable