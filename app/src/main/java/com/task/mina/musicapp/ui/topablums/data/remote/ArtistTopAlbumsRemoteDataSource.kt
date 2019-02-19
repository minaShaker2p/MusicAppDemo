package com.task.mina.musicapp.ui.topablums.data.remote

import com.task.mina.musicapp.BuildConfig
import com.task.mina.musicapp.data.remote.network.response.ArtistTopAlbumsResponse
import com.task.mina.musicapp.data.remote.network.retrofit.MusicServiceAPI
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Mina Alfy on 2/14/2019.
 */
class ArtistTopAlbumsRemoteDataSource @Inject constructor(private val musicServiceAPI: MusicServiceAPI) {

    fun getArtistTopAlbums(artistName: String): Single<ArtistTopAlbumsResponse> =
            musicServiceAPI.getArtistTopAlbums(artist = artistName, apiKey = BuildConfig.API_KEY)
}