package com.task.mina.musicapp.ui.topablums.domain.interactor

import com.task.mina.musicapp.base.domain.interactor.SingleUseCase
import com.task.mina.musicapp.data.remote.network.response.ArtistTopAlbumsResponse
import com.task.mina.musicapp.ui.topablums.domain.repository.ArtistTopAlbumsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetArtistTopAlbumsUsecase @Inject constructor(private val repository: ArtistTopAlbumsRepository) :
        SingleUseCase<String, ArtistTopAlbumsResponse>() {
    override fun build(params: String): Single<ArtistTopAlbumsResponse> =
            repository.getArtistTopAlbums(artistName = params)

}