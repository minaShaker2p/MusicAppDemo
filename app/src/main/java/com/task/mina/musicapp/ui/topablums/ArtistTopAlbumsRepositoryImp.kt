package com.task.mina.musicapp.ui.topablums

import com.task.mina.musicapp.data.remote.network.response.ArtistTopAlbumsResponse
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Mina Alfy on 2/14/2019.
 */
class ArtistTopAlbumsRepositoryImp @Inject constructor(private val remoteDataSource: ArtistTopAlbumsRemoteDataSource) : ArtistTopAlbumsRepository {
    override fun getArtistTopAlbums(artistName: String): Single<ArtistTopAlbumsResponse> = remoteDataSource.getArtistTopAlbums(artistName)

}