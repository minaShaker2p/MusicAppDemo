package com.task.mina.musicapp.injection

import com.task.mina.musicapp.ui.mainscreen.presenation.view.MainActivity
import com.task.mina.musicapp.ui.mainscreen.injection.MainScreenModule
import com.task.mina.musicapp.ui.searchscreen.injection.SearchActivityModule
import com.task.mina.musicapp.ui.searchscreen.presentation.view.activity.SearchArtistActivity
import com.task.mina.musicapp.ui.topablums.injection.ArtistTopAlbumsModule
import com.task.mina.musicapp.ui.topablums.presetation.view.activity.TopArtistAlbumsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [(SearchActivityModule::class)])
    abstract fun bindSearchActivity(): SearchArtistActivity

    @ContributesAndroidInjector(modules = [(ArtistTopAlbumsModule::class)])
    abstract fun bindArtistTopAlbumsActivity(): TopArtistAlbumsActivity

    @ContributesAndroidInjector(modules = [(MainScreenModule::class)])
    abstract fun bindMainScreenActivity(): MainActivity

}

