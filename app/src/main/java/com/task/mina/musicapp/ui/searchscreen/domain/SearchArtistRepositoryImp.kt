package com.task.mina.musicapp.ui.searchscreen.domain

import com.task.mina.musicapp.data.remote.network.response.Artist
import com.task.mina.musicapp.ui.searchscreen.data.remote.ArtistSearchRemoteDataSource
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Mina Alfy on 2/12/2019.
 */
class SearchArtistRepositoryImp @Inject constructor(private val remoteDataSource: ArtistSearchRemoteDataSource) : SearchArtistRepository {
    override fun searchArtist(artistName: String): Single<List<Artist>> {
        return remoteDataSource.search(artistName = artistName).map {
            it.results.artistMatches.artists
        }
    }
}