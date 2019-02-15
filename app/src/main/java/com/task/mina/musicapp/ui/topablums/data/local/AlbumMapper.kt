package com.task.mina.musicapp.ui.topablums.data.local

import com.task.mina.musicapp.data.local.entity.ArtistAlbumEntity
import com.task.mina.musicapp.data.remote.network.response.Album
import com.task.mina.musicapp.ui.topablums.domain.entity.AlbumUI

/**
 * Created by Mina Alfy on 2/15/2019.
 */
fun Album.map(): ArtistAlbumEntity {
    val image: String = this.image.filter {
        it.size == "large"
    }.map {
        it.text
    }.first()
    return ArtistAlbumEntity(albumImage = image
            , artistName = this.artist.name
            , albumPlayCount = this.playcount
            , albumName = this.name,
            albumUrl = this.url)
}

fun ArtistAlbumEntity.mapToUI(): AlbumUI {
    return AlbumUI(albumImage = albumImage
            , artistName = artistName
            , albumPlayCount = albumPlayCount
            , albumName = albumName,
            albumUrl = albumUrl)
}

fun Album.mapToUI(): AlbumUI {
    val image: String = this.image.filter {
        it.size == "large"
    }.map {
        it.text
    }.first()
    return AlbumUI(albumImage = image
            , artistName = this.artist.name
            , albumPlayCount = this.playcount
            , albumName = this.name,
            albumUrl = this.url)
}