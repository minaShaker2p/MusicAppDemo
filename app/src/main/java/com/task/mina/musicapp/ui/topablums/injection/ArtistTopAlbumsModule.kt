package com.task.mina.musicapp.ui.topablums.injection

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import com.task.mina.musicapp.data.local.dao.AlbumDao
import com.task.mina.musicapp.data.remote.network.retrofit.MusicServiceAPI
import com.task.mina.musicapp.ui.topablums.data.local.ArtistTopAlbumsLocalDataSource
import com.task.mina.musicapp.ui.topablums.data.remote.ArtistTopAlbumsRemoteDataSource
import com.task.mina.musicapp.ui.topablums.domain.interactor.AddAlbumsLocalUsecase
import com.task.mina.musicapp.ui.topablums.domain.interactor.DeleteAlbumsLocalUsecase
import com.task.mina.musicapp.ui.topablums.domain.repository.ArtistTopAlbumsRepository
import com.task.mina.musicapp.ui.topablums.domain.repository.ArtistTopAlbumsRepositoryImp
import com.task.mina.musicapp.ui.topablums.domain.interactor.GetArtistTopAlbumsUsecase
import com.task.mina.musicapp.ui.topablums.presetation.view.adapter.ArtistTopAlbumAdapter
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
    fun provideAddAlbumsLocalUseCase(repository: ArtistTopAlbumsRepository) =
            AddAlbumsLocalUsecase(repository)

    @Provides
    fun provideDeleteAlbumsLocalUseCase(repository: ArtistTopAlbumsRepository) =
            DeleteAlbumsLocalUsecase(repository)


    @Provides
    fun provideArtistTopAlbumsViewModel(getArtistTopAlbumsUsecase: GetArtistTopAlbumsUsecase, addAlbumsLocalUsecase: AddAlbumsLocalUsecase, deleteAlbumsLocalUsecase: DeleteAlbumsLocalUsecase) =
            ArtistTopAlbumsViewModel(getArtistTopAlbumsUsecase, addAlbumsLocalUsecase, deleteAlbumsLocalUsecase)

    @Provides
    fun provideGridLayoutManager(context: Context) =
            GridLayoutManager(context, 2)

    @Provides
    fun provideArtistTopAlbumAdapter() =
            ArtistTopAlbumAdapter()

}