package com.task.mina.musicapp.ui.topablums

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.task.mina.musicapp.data.remote.network.retrofit.MusicServiceAPI
import com.task.mina.musicapp.ui.searchscreen.ArtistListAdapter
import dagger.Module
import dagger.Provides

/**
 * Created by Mina Alfy on 2/14/2019.
 */
@Module
class ArtistTopAlbumsModule {

    @Provides
    fun provideArtistTopAlbumsRemoteDataSource(musicServiceAPI: MusicServiceAPI) =
            ArtistTopAlbumsRemoteDataSource(musicServiceAPI = musicServiceAPI)

    @Provides
    fun provideArtistTopAlbumRepository(remoteDataSource: ArtistTopAlbumsRemoteDataSource): ArtistTopAlbumsRepository =
            ArtistTopAlbumsRepositoryImp(remoteDataSource)

    @Provides
    fun provideGetArtistTopAlbumsUseCase(repository: ArtistTopAlbumsRepository) =
            GetArtistTopAlbumsUsecase(repository)

    @Provides
    fun provideArtistTopAlbumsViewModel(getArtistTopAlbumsUsecase: GetArtistTopAlbumsUsecase) =
            ArtistTopAlbumsViewModel(getArtistTopAlbumsUsecase)

    @Provides
    fun provideGridLayoutManager(context: Context) =
            GridLayoutManager(context, 2)

    @Provides
    fun provideArtistTopAlbumAdapter() =
            ArtistTopAlbumAdapter()

}