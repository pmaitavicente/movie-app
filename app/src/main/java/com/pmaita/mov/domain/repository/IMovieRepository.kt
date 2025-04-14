package com.pmaita.mov.domain.repository

import androidx.lifecycle.LiveData
import com.pmaita.mov.data.source.remote.response.GenericResponse
import com.pmaita.mov.domain.model.Movie

interface IMovieRepository {

    suspend fun getMoviesRemote(page:Int, apiKey:String) : GenericResponse
    suspend fun getMoviesLocal(limit:Int, offset:Int):List<Movie.Data>
    suspend fun addMovies(movies: List<Movie.Data>)
    suspend fun getFavoritesLocal():List<Movie.Data>
    fun getMovieById(uid: Int): LiveData<Movie.Data>
    suspend fun updateMovie(movie: Movie.Data)

}