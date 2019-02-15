package com.task.mina.musicapp.ui.topablums.domain.repository

import com.task.mina.musicapp.data.local.entity.ArtistAlbumEntity
import com.task.mina.musicapp.data.remote.network.response.ArtistTopAlbumsResponse
import com.task.mina.musicapp.ui.topablums.data.local.ArtistTopAlbumsLocalDataSource
import com.task.mina.musicapp.ui.topablums.data.remote.ArtistTopAlbumsRemoteDataSource
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Mina Alfy on 2/14/2019.
 */
class ArtistTopAlbumsRepositoryImp @Inject constructor(private val remoteDataSource: ArtistTopAlbumsRemoteDataSource, private val localDataSource: ArtistTopAlbumsLocalDataSource) : ArtistTopAlbumsRepository {


    override fun insertArtistAlbums(artistAlbums: List<ArtistAlbumEntity>): Single<Boolean> =
            localDataSource.insertArtistAlbums(albums = artistAlbums)

    override fun deleteArtistAlbums(artistAlbums: List<ArtistAlbumEntity>): Single<Boolean> =
            localDataSource.deleteArtistAlbums(artistAlbums)

    override fun getArtistTopAlbums(artistName: String): Single<ArtistTopAlbumsResponse> =
            remoteDataSource.getArtistTopAlbums(artistName)

}