package com.task.mina.musicapp.data.local.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Mina Alfy on 2/15/2019.
 */
@Entity(tableName = "Album")
data class ArtistAlbumEntity(
        @PrimaryKey
        @ColumnInfo(name = "album_name")
        var albumName: String,
        @ColumnInfo(name = "play_count")
        var albumPlayCount: Long,
        @ColumnInfo(name = "url")
        var albumUrl: String,
        @ColumnInfo(name = "artist_name")
        var artistName: String,
        @ColumnInfo(name = "album_image")
        var albumImage: String
)