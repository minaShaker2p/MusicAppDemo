package com.task.mina.musicapp.ui.searchscreen.domain

import com.task.mina.musicapp.data.remote.network.response.Artist
import io.reactivex.Single

/**
 * Created by Mina Alfy on 2/12/2019.
 */
interface SearchArtistRepository {
    fun searchArtist(artistName: String, page: Int , limit: Int): Single<List<Artist>>
}