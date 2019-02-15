package com.task.mina.musicapp.ui.topablums.presetation.viewmodel

import com.task.mina.musicapp.base.domain.exception.MusicAppException
import com.task.mina.musicapp.base.presentation.model.ObservableResource
import com.task.mina.musicapp.base.presentation.viewmodel.BaseViewModel
import com.task.mina.musicapp.data.remote.network.response.Album
import com.task.mina.musicapp.ui.topablums.domain.interactor.GetArtistTopAlbumsUsecase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Mina Alfy on 2/15/2019.
 */
class ArtistTopAlbumsViewModel @Inject constructor(private val getArtistTopAlbumsUsecase: GetArtistTopAlbumsUsecase) : BaseViewModel() {
    val mTopAlbumsObservable = ObservableResource<List<Album>>()

    fun getArtistTopAlbums(artistName: String) {
        addDisposable(getArtistTopAlbumsUsecase.build(params = artistName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    mTopAlbumsObservable.loading.postValue(true)
                }
                .doAfterTerminate {
                    mTopAlbumsObservable.loading.postValue(false)
                }
                .subscribe({
                    it?.let {
                        if (it.topalbums.album.isNotEmpty()) {
                            mTopAlbumsObservable.value = it.topalbums.album
                        } else {

                        }
                    }

                }, {
                    (it as? MusicAppException).let {
                        mTopAlbumsObservable.error.value = it
                    }
                })
        )
    }
}