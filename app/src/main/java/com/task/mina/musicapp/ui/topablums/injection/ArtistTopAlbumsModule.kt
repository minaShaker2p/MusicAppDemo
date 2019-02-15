package com.task.mina.musicapp.ui.topablums.injection

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import com.task.mina.musicapp.data.local.dao.AlbumDao
import com.task.mina.musicapp.data.remote.network.retrofit.MusicServiceAPI
import com.task.mina.musicapp.ui.topablums.data.local.ArtistTopAlbumsLocalDataSource
import com.task.mina.musicapp.ui.topablums.data.remote.ArtistTopAlbumsRemoteDataSource
import com.task.mina.musicapp.ui.topablums.domain.ArtistTopAlbumsRepository
import com.task.mina.musicapp.ui.topablums.domain.ArtistTopAlbumsRepositoryImp
import com.task.mina.musicapp.ui.topablums.domain.GetArtistTopAlbumsUsecase
import com.task.mina.musicapp.ui.topablums.presetation.view.ArtistTopAlbumAdapter
import com.task.mina.musicapp.ui.topablums.presetation.viewmodel.ArtistTopAlbumsViewModel
import dagger.Module
import dagger.Provides

/**
 * Created by Mina Alfy on 2/14/2019.
 */
@Module
class ArtistTopAlbumsModule {

    @Provides
    fun provideArtistTopAlbumsLocalDataSource(albumDao: AlbumDao) =
            ArtistTopAlbumsLocalDataSource(albumDao = albumDao)


    @Provides
    fun provideArtistTopAlbumsRemoteDataSource(musicServiceAPI: MusicServiceAPI) =
            ArtistTopAlbumsRemoteDataSource(musicServiceAPI = musicServiceAPI)

    @Provides
    fun provideArtistTopAlbumRepository(remoteDataSource: ArtistTopAlbumsRemoteDataSource, localDataSource: ArtistTopAlbumsLocalDataSource): ArtistTopAlbumsRepository =
            ArtistTopAlbumsRepositoryImp(remoteDataSource, localDataSource)

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