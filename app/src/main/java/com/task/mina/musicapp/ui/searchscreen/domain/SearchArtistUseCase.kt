package com.task.mina.musicapp.ui.searchscreen.domain

import com.task.mina.musicapp.base.domain.interactor.SingleUseCase
import com.task.mina.musicapp.data.remote.network.response.Artist
import com.task.mina.musicapp.ui.searchscreen.data.remote.apibody.SearchAPIBody
import io.reactivex.Single
import javax.inject.Inject

class SearchArtistUseCase @Inject constructor(private val repository: SearchArtistRepository) :
        SingleUseCase<SearchAPIBody, List<Artist>>() {
    override fun build(params: SearchAPIBody): Single<List<Artist>> =
            repository.searchArtist(artistName = params.artistName,page = params.page,limit = params.limit)

}