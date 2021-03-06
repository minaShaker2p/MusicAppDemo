package com.task.mina.musicapp.ui.topablums.data.local

import com.task.mina.musicapp.data.local.dao.AlbumDao
import com.task.mina.musicapp.data.local.entity.ArtistAlbumEntity
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Mina Alfy on 2/14/2019.
 */
class ArtistTopAlbumsLocalDataSource @Inject constructor(private val albumDao: AlbumDao) {

    fun insertArtistAlbums(albums: List<ArtistAlbumEntity>): Single<Boolean> =
            Single.create {
                albumDao.insertAlbums(albums)
                it.onSuccess(true)
            }


    fun deleteArtistAlbums(albums: List<ArtistAlbumEntity>): Single<Boolean> =
            Single.create {
                albumDao.deleteAlbums(albums)
                it.onSuccess(true)
            }


}