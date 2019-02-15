package com.task.mina.musicapp.data.remote.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


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
@Parcelize
data class Album(
        val artist: ArtistAlbum,
        val image: List<AlbumImage>,
        val mbid: String,
        val name: String,
        val playcount: Int,
        val url: String
) : Parcelable

@Parcelize
data class ArtistAlbum(
        val mbid: String,
        val name: String,
        val url: String
) : Parcelable

@Parcelize
data class AlbumImage(
        @SerializedName("#text")
        val text: String,
        val size: String
) : Parcelable