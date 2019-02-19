package com.task.mina.musicapp.ui.mainscreen.domain.repository

import android.arch.lifecycle.LiveData
import com.task.mina.musicapp.data.local.entity.ArtistAlbumEntity
import com.task.mina.musicapp.ui.mainscreen.data.local.MainScreenLocalDataSource
import javax.inject.Inject

/**
 * Created by Mina Alfy on 2/15/2019.
 */
class MainScreenRepositoryImp @Inject constructor(private val localDataSource: MainScreenLocalDataSource) : MainScreenRepository {
    override fun getAllArtistAlbums(): LiveData<List<ArtistAlbumEntity>> =
            localDataSource.getAllAlbums()

}