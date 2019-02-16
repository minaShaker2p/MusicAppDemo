package com.task.mina.musicapp.data.remote.network.response

import com.google.gson.annotations.SerializedName


data class ArtistTopAlbumsResponse(
        val topalbums: Topalbums
)

//----------------------------------------------
data class Topalbums(
        val attr: Attr,
        val album: List<Album>
)
//-----------------------------------------------

data class Attr(
        val artist: String,
        val page: String,
        val perPage: String,
        val total: String,
        val totalPages: String
)

//-----------------------------------------------
data class Album(
        val artist: ArtistAlbum,
        val image: List<AlbumImage>,
        val mbid: String,
        val name: String,
        val playcount: Long,
        val url: String
)

data class ArtistAlbum(
        val mbid: String,
        val name: String,
        val url: String
)

data class AlbumImage(
        @SerializedName("#text")
        val text: String,
        val size: String
)