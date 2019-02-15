package com.task.mina.musicapp.ui.searchscreen.injection

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.task.mina.musicapp.data.remote.network.retrofit.MusicServiceAPI
import com.task.mina.musicapp.ui.searchscreen.data.remote.ArtistSearchRemoteDataSource
import com.task.mina.musicapp.ui.searchscreen.domain.SearchArtistRepository
import com.task.mina.musicapp.ui.searchscreen.domain.SearchArtistRepositoryImp
import com.task.mina.musicapp.ui.searchscreen.domain.SearchArtistUseCase
import com.task.mina.musicapp.ui.searchscreen.presentation.viewmodel.SearchArtistViewmodel
import com.task.mina.musicapp.ui.searchscreen.presentation.view.ArtistListAdapter
import dagger.Module
import dagger.Provides

/**
 * Created by Mina Alfy on 2/12/2019.
 */
@Module
class SearchActivityModule {
    @Provides
    fun provideArtistTopAlbumsRemoteDataSource(musicServiceAPI: MusicServiceAPI) =
            ArtistSearchRemoteDataSource(musicServiceAPI = musicServiceAPI)

    @Provides
    fun provideSearchArtistRepository(remoteDataSource: ArtistSearchRemoteDataSource): SearchArtistRepository =
            SearchArtistRepositoryImp(remoteDataSource)

    @Provides
    fun provideSearchArtistUseCase(searchArtistRepository: SearchArtistRepository) =
            SearchArtistUseCase(searchArtistRepository)

    @Provides
    fun provideSearchArtistViewmodel(userCase: SearchArtistUseCase): SearchArtistViewmodel =
            SearchArtistViewmodel(userCase)

    @Provides
    fun providelinearLayoutManager(context: Context) =
            LinearLayoutManager(context)

    @Provides
    fun provideArtistListAdapter() =
            ArtistListAdapter()

}