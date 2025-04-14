package com.pmaita.mov.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.pmaita.mov.data.source.local.entity.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT uid, poster_path, title, vote_average, release_date, " +
            "overview, popularity, favorite " +
            "FROM movie ORDER BY popularity DESC LIMIT :limit OFFSET :offset")
    fun getByPagination(limit:Int, offset:Int): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovies(movies: List<MovieEntity>)

    @Query("SELECT uid, poster_path, title, vote_average, release_date, " +
            "overview, popularity, favorite " +
            "FROM movie WHERE favorite = 1 ORDER BY popularity")
    fun getFavorites(): List<MovieEntity>

    @Query("SELECT uid, poster_path, title, vote_average, release_date, " +
            "overview, popularity, favorite  FROM movie WHERE uid = :uid")
    fun getMovieById(uid: Int): LiveData<MovieEntity>

    @Update
    suspend fun updateMovie(movie: MovieEntity)

}