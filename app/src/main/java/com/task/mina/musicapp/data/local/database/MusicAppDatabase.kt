package com.task.mina.musicapp.data.local.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.task.mina.musicapp.data.local.dao.AlbumDao
import com.task.mina.musicapp.data.local.entity.ArtistAlbumEntity

/**
 * Created by Mina Alfy on 2/15/2019.
 */
@Database(entities = [ArtistAlbumEntity::class], version = 1, exportSchema = false)
public abstract class MusicAppDatabase : RoomDatabase() {
    companion object {
        val DATABASE_NAME: String = "MusicAppDB"
    }

    abstract fun AlbumDao(): AlbumDao
}