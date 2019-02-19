package com.task.mina.musicapp.ui.mainscreen.injection

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import com.task.mina.musicapp.data.local.dao.AlbumDao
import com.task.mina.musicapp.ui.mainscreen.data.local.MainScreenLocalDataSource
import com.task.mina.musicapp.ui.mainscreen.domain.interactor.GetArtistAlbumsLocalUsecase
import com.task.mina.musicapp.ui.mainscreen.domain.repository.MainScreenRepository
import com.task.mina.musicapp.ui.mainscreen.domain.repository.MainScreenRepositoryImp
import com.task.mina.musicapp.ui.mainscreen.presenation.viewmodel.MainScreenViewModel
import com.task.mina.musicapp.ui.topablums.presetation.view.adapter.ArtistTopAlbumAdapter
import dagger.Module
import dagger.Provides

/**
 * Created by Mina Alfy on 2/15/2019.
 */
@Module
class MainScreenModule {

    @Provides
    fun provideMainScreenLocalDataSource(albumDao: AlbumDao) =
            MainScreenLocalDataSource(albumDao)

    @Provides
    fun provideMainScreenRepository(mainScreenLocalDataSource: MainScreenLocalDataSource): MainScreenRepository =
            MainScreenRepositoryImp(mainScreenLocalDataSource)

    @Provides
    fun provideGetAlbumsLocalUseCase(repository: MainScreenRepository) =
            GetArtistAlbumsLocalUsecase(repository)

    @Provides
    fun provideMainScreenViewmodel(localUsecase: GetArtistAlbumsLocalUsecase) =
            MainScreenViewModel(localUsecase)


    @Provides
    fun provideGridLayoutManager(context: Context) =
            GridLayoutManager(context, 2)

    @Provides
    fun provideArtistTopAlbumAdapter() =
            ArtistTopAlbumAdapter()


}