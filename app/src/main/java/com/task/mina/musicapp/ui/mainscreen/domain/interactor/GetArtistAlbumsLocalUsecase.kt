package com.task.mina.musicapp.ui.mainscreen.domain.interactor

import android.arch.lifecycle.LiveData
import com.task.mina.musicapp.base.domain.interactor.SingleUseCaseNoParam
import com.task.mina.musicapp.data.local.entity.ArtistAlbumEntity
import com.task.mina.musicapp.data.remote.network.response.ArtistTopAlbumsResponse
import com.task.mina.musicapp.ui.mainscreen.domain.repository.MainScreenRepository
import com.task.mina.musicapp.ui.topablums.domain.repository.ArtistTopAlbumsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetArtistAlbumsLocalUsecase @Inject constructor(private val repository: MainScreenRepository) {
    fun build(): LiveData<List<ArtistAlbumEntity>> =
            repository.getAllArtistAlbums()
}