package com.task.mina.musicapp.ui.mainscreen.domain.repository

import android.arch.lifecycle.LiveData
import com.task.mina.musicapp.data.local.entity.ArtistAlbumEntity

/**
 * Created by Mina Alfy on 2/15/2019.
 */
interface MainScreenRepository {
    fun getAllArtistAlbums(): LiveData<List<ArtistAlbumEntity>>
}