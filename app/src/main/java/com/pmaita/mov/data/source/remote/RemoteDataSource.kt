package com.pmaita.mov.data.source.remote

import com.pmaita.mov.data.source.remote.response.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteDataSource {

    @GET("upcoming")
    suspend fun getMovies(
        @Query("page") page:Int,
        @Query("api_key") apiKey:String
    ): Response<MoviesResponse>

}