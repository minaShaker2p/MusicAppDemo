package com.task.mina.musicapp.base.presentation.view.adapter

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class ItemDecoration(private var space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView, state: RecyclerView.State) {
        with(outRect) {
            bottom = space
        }
    }
}