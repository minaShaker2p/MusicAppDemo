package com.task.mina.musicapp.ui.mainscreen.presenation.viewmodel

import android.arch.lifecycle.LiveData
import com.task.mina.musicapp.base.presentation.viewmodel.BaseViewModel
import com.task.mina.musicapp.data.local.entity.ArtistAlbumEntity
import com.task.mina.musicapp.ui.mainscreen.domain.interactor.GetArtistAlbumsLocalUsecase
import javax.inject.Inject

/**
 * Created by Mina Alfy on 2/15/2019.
 */
class MainScreenViewModel @Inject constructor(private val getAlbumsUseCase: GetArtistAlbumsLocalUsecase) : BaseViewModel() {

     fun getAllStoredLocalAlbums(): LiveData<List<ArtistAlbumEntity>> {

        return getAlbumsUseCase.build()
    }
}