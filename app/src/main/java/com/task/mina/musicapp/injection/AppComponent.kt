package com.task.mina.musicapp.injection

import android.app.Application
import com.task.mina.musicapp.MusicApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Mina Alfy on 2/9/2019.
 */
@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityBuilder::class,
    NetworkModule::class,
    MusicDBModule::class])
interface AppComponent {

    fun inject(app: MusicApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

}
