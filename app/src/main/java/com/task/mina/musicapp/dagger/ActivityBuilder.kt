package com.task.mina.musicapp.dagger

import com.task.mina.musicapp.ui.searchscreen.injection.SearchActivityModule
import com.task.mina.musicapp.ui.searchscreen.presentation.view.SearchArtistActivity
import com.task.mina.musicapp.ui.topablums.injection.ArtistTopAlbumsModule
import com.task.mina.musicapp.ui.topablums.presetation.view.TopArtistAlbumsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [(SearchActivityModule::class)])
    abstract fun bindSearchActivity(): SearchArtistActivity

    @ContributesAndroidInjector(modules = [(ArtistTopAlbumsModule::class)])
    abstract fun bindArtistTopAlbumsActivity(): TopArtistAlbumsActivity

}

