package com.task.mina.musicapp.ui.searchscreen

import com.task.mina.musicapp.BuildConfig
import com.task.mina.musicapp.data.remote.network.response.Artist
import com.task.mina.musicapp.data.remote.network.retrofit.MusicServiceAPI
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Mina Alfy on 2/12/2019.
 */
class SearchArtistRepositoryImp @Inject constructor(private val musicServiceAPI: MusicServiceAPI) : SearchArtistRepository {
    override fun searchArtist(artistName: String): Single<List<Artist>> {
       return musicServiceAPI.Search(artist = artistName, apiKey = BuildConfig.API_KEY).map {
            it.results.artistMatches.artists
        }
    }
}