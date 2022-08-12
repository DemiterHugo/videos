package com.example.imagevideos.ui.detail

import androidx.databinding.BindingAdapter
import com.example.imagevideos.model.database.Image


@BindingAdapter("imageInfo")
fun InfoImage.updateInfoImage(image: Image?){
    if (image != null) setImage(image = image)
}