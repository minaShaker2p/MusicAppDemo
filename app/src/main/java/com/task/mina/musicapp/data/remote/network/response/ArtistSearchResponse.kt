package com.task.mina.musicapp.data.remote.network.response

import com.google.gson.annotations.SerializedName


/**
 * Created by Mina Alfy on 2/11/2019.
 */
data class ArtistSearchResponse(@SerializedName("results") val results: Results)

data class Results(@SerializedName("artistmatches") val artistMatches: ArtistMatches)
data class ArtistMatches(@SerializedName("artist") val artists: List<Artist>)
data class Artist(
        @SerializedName("name")
        val name: String,
        @SerializedName("listeners")
        val listeners: String,
        @SerializedName("mbid")
        val mbid: String,
        @SerializedName("url")
        val url: String,
        @SerializedName("streamable")
        val streamable: String,
        @SerializedName("image")
        val image: List<Image>
)

data class Image(
        @SerializedName("#text")
        val text: String,
        @SerializedName("size")
        val size: String
)