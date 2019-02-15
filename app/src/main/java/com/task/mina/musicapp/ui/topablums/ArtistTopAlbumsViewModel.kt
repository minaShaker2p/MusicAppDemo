package com.task.mina.musicapp.ui.topablums

import com.task.mina.musicapp.base.presentation.viewmodel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Mina Alfy on 2/15/2019.
 */
class ArtistTopAlbumsViewModel @Inject constructor(private val getArtistTopAlbumsUsecase: GetArtistTopAlbumsUsecase) : BaseViewModel() {

    fun getArtistTopAlbums(artistName: String) {
        addDisposable(getArtistTopAlbumsUsecase.build(params = artistName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                }, {

                })
        )
    }
}