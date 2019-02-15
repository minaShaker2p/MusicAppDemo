package com.task.mina.musicapp.dagger

import android.arch.persistence.room.Room
import android.content.Context
import com.task.mina.musicapp.data.local.dao.AlbumDao
import com.task.mina.musicapp.data.local.database.MusicAppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by Mina Alfy on 2/15/2019.
 */
@Module
class MusicDBModule {
    @Singleton
    @Provides
    fun provideMusicAppDatabase(context: Context): MusicAppDatabase =
            Room.databaseBuilder(context,
                    MusicAppDatabase::class.java, MusicAppDatabase.DATABASE_NAME)
                    .build()

    @Singleton
    @Provides
    fun provideAlbumDao(tvMazeDatabase: MusicAppDatabase): AlbumDao {
        return tvMazeDatabase.AlbumDao()
    }
}