package com.task.mina.musicapp.ui.searchscreen.data.remote

import com.task.mina.musicapp.BuildConfig
import com.task.mina.musicapp.data.remote.network.response.ArtistSearchResponse
import com.task.mina.musicapp.data.remote.network.response.ArtistTopAlbumsResponse
import com.task.mina.musicapp.data.remote.network.retrofit.MusicServiceAPI
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Mina Alfy on 2/14/2019.
 */
class ArtistSearchRemoteDataSource @Inject constructor(private val musicServiceAPI: MusicServiceAPI) {

    fun search(artistName: String): Single<ArtistSearchResponse> =
            musicServiceAPI.search(artist = artistName, apiKey = BuildConfig.API_KEY)
}