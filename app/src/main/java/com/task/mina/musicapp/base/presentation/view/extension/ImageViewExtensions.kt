package com.task.mina.musicapp.base.presentation.view.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


fun ImageView.loadFromUrl(url: String, placeholder: Int? = null) {
    val glideApp = Glide.with(context)
            .load(url)

    placeholder?.let {
        glideApp.apply(RequestOptions().placeholder(placeholder))
    }

    glideApp.into(this)
}


