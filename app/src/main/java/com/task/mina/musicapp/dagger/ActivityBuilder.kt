package com.task.mina.musicapp.dagger

import com.task.mina.musicapp.ui.searchscreen.SearchActivityModule
import com.task.mina.musicapp.ui.searchscreen.SearchArtistActivity
import com.task.mina.musicapp.ui.topablums.ArtistTopAlbumsModule
import com.task.mina.musicapp.ui.topablums.TopArtistAlbumsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [(SearchActivityModule::class)])
    abstract fun bindSearchActivity(): SearchArtistActivity

    @ContributesAndroidInjector(modules = [(ArtistTopAlbumsModule::class)])
    abstract fun bindArtistTopAlbumsActivity(): TopArtistAlbumsActivity

}

