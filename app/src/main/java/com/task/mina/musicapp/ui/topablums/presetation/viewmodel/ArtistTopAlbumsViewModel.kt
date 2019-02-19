package com.task.mina.musicapp.ui.topablums.presetation.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.task.mina.musicapp.base.domain.exception.MusicAppException
import com.task.mina.musicapp.base.presentation.model.ObservableResource
import com.task.mina.musicapp.base.presentation.viewmodel.BaseViewModel
import com.task.mina.musicapp.data.local.entity.ArtistAlbumEntity
import com.task.mina.musicapp.data.remote.network.response.Album
import com.task.mina.musicapp.ui.topablums.data.local.map
import com.task.mina.musicapp.ui.topablums.data.local.mapToUI
import com.task.mina.musicapp.ui.topablums.domain.entity.AlbumUI
import com.task.mina.musicapp.ui.topablums.domain.interactor.AddAlbumsLocalUsecase
import com.task.mina.musicapp.ui.topablums.domain.interactor.DeleteAlbumsLocalUsecase
import com.task.mina.musicapp.ui.topablums.domain.interactor.GetArtistTopAlbumsUsecase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Mina Alfy on 2/15/2019.
 */
class ArtistTopAlbumsViewModel @Inject constructor(private val getArtistTopAlbumsUsecase: GetArtistTopAlbumsUsecase
                                                   , private val addAlbumsLocalUsecase: AddAlbumsLocalUsecase
                                                   , private val deleteAlbumsLocalUsecase: DeleteAlbumsLocalUsecase
) : BaseViewModel() {
    private val albumsList = mutableListOf<Album>()
    val mArtistAlbums = MutableLiveData<List<AlbumUI>>()
    val mTopAlbumsObservable = ObservableResource<String>()

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
                            // save copy of list handle save and delete objects
                            albumsList.addAll(it.topalbums.album.toMutableList())
                            mArtistAlbums.value = (it.topalbums.album.map { it.mapToUI() })
                        }
                    }

                }, {
                    (it as? MusicAppException).let {
                        mTopAlbumsObservable.error.value = it
                    }
                })
        )
    }


    fun storeAristAlbum() {
        if (albumsList.size > 0) {

            storeArtistAlbumsFromDB(albumsList.map { it.map() })
        } else {
            // in case of empty values
            val message = "Sorry,No Albums"
            mTopAlbumsObservable.value = message
        }
    }

    fun deleteAristAlbum() {
        if (albumsList.size > 0) {
            deleteArtistAlbumsFromDB(albumsList.map { it.map() })
        } else {
            // in case of empty values
            val message = "Sorry,No Albums"
            mTopAlbumsObservable.value = message


        }
    }

    private fun storeArtistAlbumsFromDB(albums: List<ArtistAlbumEntity>) {
        addDisposable(addAlbumsLocalUsecase.build(albums)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val message = "Albums saved successfully =D"
                    mTopAlbumsObservable.value = message

                }, {
                    val message = "Unexpected Error"
                    mTopAlbumsObservable.value = message

                }
                ))
    }

    private fun deleteArtistAlbumsFromDB(albums: List<ArtistAlbumEntity>) {
        addDisposable(deleteAlbumsLocalUsecase.build(albums)
                .subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val message = "Albums Deleted successfully =D"
                    mTopAlbumsObservable.value = message


                }, {
                    val message = "Unexpected Error"
                    mTopAlbumsObservable.value = message

                }
                ))
    }
}