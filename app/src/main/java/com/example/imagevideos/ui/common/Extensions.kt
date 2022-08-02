package com.example.imagevideos.ui.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide


fun ViewGroup.inflate(layoutRes:Int, attachToRoot: Boolean = true):View{
    return LayoutInflater.from(context).inflate(layoutRes,this,attachToRoot)
}

inline fun <T>basicDiffUtil(
    crossinline areItemsTheSame: (T,T) -> Boolean = { old, new -> old ==new },
    crossinline areContentsTheSame: (T,T) -> Boolean = {old, new -> old == new}
): DiffUtil.ItemCallback<T> {
    return object: DiffUtil.ItemCallback<T>(){
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {

            return areItemsTheSame(oldItem,newItem)
        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return areContentsTheSame(oldItem, newItem)
        }

    }
}

fun ImageView.loadUrl(url: String) {
    Glide.with(context).load(url).into(this)
}

var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }