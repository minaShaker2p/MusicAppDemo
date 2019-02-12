package com.task.mina.musicapp.ui.searchscreen

import com.task.mina.musicapp.data.remote.network.retrofit.MusicServiceAPI
import dagger.Module
import dagger.Provides

/**
 * Created by Mina Alfy on 2/12/2019.
 */
@Module
class SearchActivityModule {

    @Provides
    fun provideSearchArtistRepository(musicServiceAPI: MusicServiceAPI): SearchArtistRepository =
            SearchArtistRepositoryImp(musicServiceAPI)

    @Provides
    fun provideSearchArtistUseCase(searchArtistRepository: SearchArtistRepository) =
            SearchArtistUseCase(searchArtistRepository)

    @Provides
    fun provideSearchArtistViewmodel(userCase: SearchArtistUseCase): SearchArtistViewmodel =
            SearchArtistViewmodel(userCase)

}