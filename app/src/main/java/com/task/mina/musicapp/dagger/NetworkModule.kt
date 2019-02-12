package com.task.mina.musicapp.dagger

import com.task.mina.musicapp.BuildConfig
import com.task.mina.musicapp.data.remote.network.retrofit.MusicServiceAPI
import com.task.mina.musicapp.data.remote.network.retrofit.RetrofitClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

import javax.inject.Named
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    @Singleton
    @Named("BASE_URL")
    fun provideBaseURL() = BuildConfig.BASE_URL


    @Provides
    @Singleton
    fun provideHttpClient() = OkHttpClient()


    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor()


    @Provides
    @Singleton
    fun provideRetrofit() = Retrofit.Builder()


    @Provides
    @Singleton
    fun provideRetrofitClient(@Named("BASE_URL") baseURL: String, httpClient: OkHttpClient, httpLoggingInterceptor: HttpLoggingInterceptor, builder: Retrofit.Builder)
            : Retrofit = RetrofitClient(baseURL = baseURL, httpClient = httpClient.newBuilder(), httpLoggingInterceptor = httpLoggingInterceptor, builder = builder).getInstance()

    @Provides
    @Singleton
    fun provideMusicServiceAPI(retrofit: Retrofit)
            : MusicServiceAPI = retrofit.create(MusicServiceAPI::class.java)

}