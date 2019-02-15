package com.task.mina.musicapp.ui.topablums.domain.interactor

import com.task.mina.musicapp.base.domain.interactor.SingleUseCase
import com.task.mina.musicapp.data.local.entity.ArtistAlbumEntity
import com.task.mina.musicapp.ui.topablums.domain.repository.ArtistTopAlbumsRepository
import io.reactivex.Single
import javax.inject.Inject

class DeleteAlbumsLocalUsecase @Inject constructor(private val repository: ArtistTopAlbumsRepository) :
        SingleUseCase<List<ArtistAlbumEntity>, Boolean>() {
    override fun build(params: List<ArtistAlbumEntity>): Single<Boolean> =
            repository.deleteArtistAlbums(artistAlbums = params)

}