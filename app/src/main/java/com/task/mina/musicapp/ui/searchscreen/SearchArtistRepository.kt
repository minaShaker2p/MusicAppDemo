package com.task.mina.musicapp.ui.searchscreen

import com.task.mina.musicapp.data.remote.network.response.Artist
import io.reactivex.Single

/**
 * Created by Mina Alfy on 2/12/2019.
 */
interface SearchArtistRepository {
    fun searchArtist(artistName: String): Single<List<Artist>>
}