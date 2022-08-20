package com.example.imagevideos.ui.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.example.imagevideos.App
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


fun ViewGroup.inflate(layoutRes:Int, attachToRoot: Boolean = true):View{
    return LayoutInflater.from(context).inflate(layoutRes,this,attachToRoot)
}

/*inline fun <T>basicDiffUtil(
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
}*/

fun ImageView.loadUrl(url: String) {
    Glide.with(context).load(url).into(this)
}

var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

fun <T> LifecycleOwner.collectFlow(flow: Flow<T>, state: Lifecycle.State = Lifecycle.State.STARTED, body: (T)-> Unit){
    lifecycleScope.launch {
        repeatOnLifecycle(state){
            flow.collect{
                body(it)
            }
        }
    }
}

val Context.app: App
    get() = applicationContext as App

val Fragment.app: App
    get() = requireContext().app