package com.task.mina.musicapp.data.local.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.task.mina.musicapp.data.local.entity.ArtistAlbumEntity

/**
 * Created by Mina Alfy on 2/15/2019.
 */
@Dao
interface AlbumDao {

    @Query("SELECT * from Album")
    fun getAllAlbums(): LiveData<List<ArtistAlbumEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(album: ArtistAlbumEntity)
}
