package com.task.mina.musicapp.ui.searchscreen

import com.task.mina.musicapp.base.domain.interactor.SingleUseCase
import com.task.mina.musicapp.data.remote.network.response.Artist
import io.reactivex.Single
import javax.inject.Inject

class SearchArtistUseCase @Inject constructor(private val repository: SearchArtistRepository) :
        SingleUseCase<String, List<Artist>>() {
    override fun build(params: String): Single<List<Artist>> =
            repository.searchArtist(artistName = params)

}