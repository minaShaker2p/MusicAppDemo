package com.task.mina.musicapp.data.local.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.task.mina.musicapp.data.local.entity.ArtistAlbumEntity
import io.reactivex.Single

/**
 * Created by Mina Alfy on 2/15/2019.
 */
@Dao
interface AlbumDao {

    @Query("SELECT * from Album")
    fun getAllAlbums(): LiveData<List<ArtistAlbumEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbums(album: List<ArtistAlbumEntity>): Single<Boolean>

    @Delete
    fun deleteAlbums(album: List<ArtistAlbumEntity>): Single<Boolean>
}
