package com.example.imagevideos.ui.main

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.imagevideos.model.database.Image
import com.example.imagevideos.ui.common.loadUrl


@BindingAdapter("itemsImage")
fun RecyclerView.setItems(images: List<Image>?){
    if (images != null){
        (adapter as? ImagesAdapter)?.submitList(images)
    }
}

@BindingAdapter("urlImage")
fun ImageView.bindUrl(url: String?){
    if(url != null) loadUrl(url)
}