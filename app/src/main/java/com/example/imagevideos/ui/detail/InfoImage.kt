package com.example.imagevideos.ui.detail

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.example.imagevideos.model.network.apientities.ApiImage

class InfoImage@JvmOverloads constructor(context: Context, attrs: AttributeSet?=null, deffStyleAttr: Int=0): AppCompatTextView(context, attrs, deffStyleAttr) {

    fun setImage(image: ApiImage){
        with(image){
          text = buildSpannedString {

              bold { append("Views: ") }
              appendLine(views.toString())

              bold { append("Downloads: ") }
              appendLine(downloads.toString())

              bold { append("Likes: ") }
              appendLine(likes.toString())

              bold { append("Coments: ") }
              appendLine(comments.toString())
          }
        }
    }
}