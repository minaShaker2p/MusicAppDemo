package com.task.mina.musicapp.ui.searchscreen

import android.util.Log
import com.task.mina.musicapp.base.domain.exception.MusicAppException
import com.task.mina.musicapp.base.presentation.model.ObservableResource
import com.task.mina.musicapp.base.presentation.viewmodel.BaseViewModel
import com.task.mina.musicapp.data.remote.network.response.Artist
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Mina Alfy on 2/12/2019.
 */
class SearchArtistViewmodel @Inject constructor(private val searchArtistUseCase: SearchArtistUseCase) : BaseViewModel() {
    val mSearchObservable by lazy { ObservableResource<List<Artist>>() }
    fun search(artistName: String) {
        if (artistName.isNotEmpty()) {
            addDisposable(searchArtistUseCase.build(params = artistName).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(
                            {
                                it?.let {
                                    mSearchObservable.postValue(it)
                                }

                            }, { error ->
                        (error is MusicAppException).let {
                            mSearchObservable.error.postValue(error as MusicAppException)
                        }
                    }
                    ))

        } else {
        }
    }
}