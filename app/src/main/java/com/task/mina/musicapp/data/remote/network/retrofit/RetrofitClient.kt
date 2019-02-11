package com.task.mina.musicapp.data.remote.network.retrofit

import com.google.gson.GsonBuilder
import com.task.mina.musicapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Mina Alfy on 2/11/2019.
 */
class RetrofitClient {


    companion object {
        fun getInstance(baseURL: String): Retrofit {
            val httpClient = OkHttpClient.Builder()
            val builder = Retrofit.Builder()
            if (BuildConfig.DEBUG) {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                httpClient.addInterceptor(logging)
            }

            val gson = GsonBuilder()
                    .setLenient()
                    .create()

            builder.baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

            builder.client(httpClient.build())
            return builder.build()
        }

    }
}



