package com.task.mina.musicapp.ui.mainscreen.data.local

import android.arch.lifecycle.LiveData
import com.task.mina.musicapp.data.local.dao.AlbumDao
import com.task.mina.musicapp.data.local.entity.ArtistAlbumEntity
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Mina Alfy on 2/14/2019.
 */
class MainScreenLocalDataSource @Inject constructor(private val albumDao: AlbumDao) {

    fun getAllAlbums(): LiveData<List<ArtistAlbumEntity>> =
            albumDao.getAllAlbums()


}