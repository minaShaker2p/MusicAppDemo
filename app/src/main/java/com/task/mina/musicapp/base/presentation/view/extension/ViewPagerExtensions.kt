package com.task.mina.musicapp.base.presentation.view.extension

import android.support.v4.view.ViewPager

fun ViewPager.onPageSelected(pageSelected: (Int) -> Unit) {
    this.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

        }

        override fun onPageSelected(position: Int) {
            pageSelected(position)
        }

        override fun onPageScrollStateChanged(state: Int) {}
    })
}


