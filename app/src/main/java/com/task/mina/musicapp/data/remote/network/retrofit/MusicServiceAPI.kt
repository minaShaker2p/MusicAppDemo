package com.task.mina.musicapp.data.remote.network.retrofit

import com.task.mina.musicapp.data.remote.network.response.ArtistSearchResponse
import com.task.mina.musicapp.data.remote.network.response.ArtistTopAlbumsResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Mina Alfy on 2/11/2019.
 */
interface MusicServiceAPI {
    @GET("2.0/?method=artist.search&format=json")
    fun search(@Query("artist") artist: String,@Query("page") page: Int,@Query("limit") limit: Int, @Query("api_key") apiKey: String): Single<ArtistSearchResponse>
//--------------------------------------------------------------------------------------------------------------------------------

    @GET("2.0/?method=artist.gettopalbums&format=json")
    fun getArtistTopAlbums(@Query("artist") artist: String, @Query("api_key") apiKey: String): Single<ArtistTopAlbumsResponse>

//----------------------------------------------------------------------------------------------------------------------------

}