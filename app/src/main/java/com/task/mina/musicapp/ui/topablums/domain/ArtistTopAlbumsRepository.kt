package com.task.mina.musicapp.ui.topablums.domain

import com.task.mina.musicapp.data.remote.network.response.ArtistTopAlbumsResponse
import io.reactivex.Single

/**
 * Created by Mina Alfy on 2/14/2019.
 */
interface ArtistTopAlbumsRepository {
    fun getArtistTopAlbums(artistName: String): Single<ArtistTopAlbumsResponse>
}