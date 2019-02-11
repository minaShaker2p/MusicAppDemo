package com.task.mina.musicapp

import android.app.Application
import com.task.mina.musicapp.dagger.DaggerAppComponent

/**
 * Created by Mina Alfy on 2/9/2019.
 */
class MusicApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }
}