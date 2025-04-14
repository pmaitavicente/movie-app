package com.pmaita.mov.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.pmaita.mov.data.repository.mapper.MovieEntityMapper
import com.pmaita.mov.data.repository.mapper.MovieMapper
import com.pmaita.mov.data.source.local.dao.MovieDao
import com.pmaita.mov.data.source.remote.RemoteDataSource
import com.pmaita.mov.data.source.remote.response.GenericResponse
import com.pmaita.mov.domain.model.Movie
import com.pmaita.mov.domain.repository.IMovieRepository
import java.lang.Exception

class MovieRepository(
    private val remote:RemoteDataSource,
    private val dao:MovieDao,
    private val mapper: MovieMapper,
    private val mapperEntity: MovieEntityMapper
) : IMovieRepository {

    override suspend fun getMoviesRemote(
        page:Int, apiKey:String
    ) : GenericResponse {
        return try {
            val response = remote.getMovies(page, apiKey)
            if(response.isSuccessful) {
                val result = response.body()!!.results
                val map = result.map { mapper.map(it) }
                addMovies(map)
                GenericResponse(map)
            } else {
                GenericResponse(null, "Error")
            }
        } catch (e:Exception) {
            GenericResponse(null, e.message.toString())
        }
    }

    override suspend fun getMoviesLocal(
        limit:Int,
        offset:Int
    ): List<Movie.Data> {
        val getAll = dao.getByPagination(limit, offset)
        return getAll.map { mapperEntity.map(it) }
    }

    override suspend fun addMovies(
        movies: List<Movie.Data>
    ) {
        val map = movies.map { mapperEntity.inverseMap(it) }
        dao.addMovies(map)
    }

    override suspend fun getFavoritesLocal(): List<Movie.Data> {
        val favorites = dao.getFavorites()
        return favorites.map { mapperEntity.map(it) }
    }

    override fun getMovieById(uid: Int): LiveData<Movie.Data> {
        return dao.getMovieById(uid).map {
            mapperEntity.map(it)
        }
    }

    override suspend fun updateMovie(movie: Movie.Data) {
        val inverse = mapperEntity.inverseMap(movie)
        dao.updateMovie(inverse)
    }

}