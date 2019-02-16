package com.task.mina.musicapp.ui.searchscreen.presentation.viewmodel

import com.task.mina.musicapp.base.domain.exception.MusicAppException
import com.task.mina.musicapp.base.presentation.model.ObservableResource
import com.task.mina.musicapp.base.presentation.viewmodel.BaseViewModel
import com.task.mina.musicapp.data.remote.network.response.Artist
import com.task.mina.musicapp.ui.searchscreen.domain.SearchArtistUseCase
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
            addDisposable(searchArtistUseCase.build(params = artistName)
                    .subscribeOn(Schedulers.io())
                    .doOnSubscribe {
                        mSearchObservable.loading.postValue(true)
                    }
                    .doAfterTerminate {
                        mSearchObservable.loading.postValue(false)
                    }
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(
                            {
                                it?.let {
                                    mSearchObservable.value = it
                                }
                            }, { error ->
                        // as? it safe cast operator
                        (error as? MusicAppException).let {
                            mSearchObservable.error.postValue(it)
                        }
                    }
                    ))

        } else {
        }
    }
}