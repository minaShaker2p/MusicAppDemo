package com.task.mina.musicapp.dagger

import com.task.mina.musicapp.BuildConfig
import com.task.mina.musicapp.data.remote.network.retrofit.MusicServiceAPI
import com.task.mina.musicapp.data.remote.network.retrofit.RetrofitClient
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

import javax.inject.Named
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    @Named("BASE_URL")
    fun provideBaseURL(): String {
        return BuildConfig.BASE_URL
    }


    @Provides
    @Singleton
    fun provideRetrofitClient(@Named("BASE_URL") baseURL: String)
            : Retrofit = RetrofitClient.getInstance(baseURL = baseURL)

    @Provides
    @Singleton
    fun provideMusicServiceAPI(retrofit: Retrofit)
            : MusicServiceAPI = retrofit.create(MusicServiceAPI::class.java)

}