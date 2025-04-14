package com.pmaita.mov.data.repository.mapper

import com.pmaita.mov.data.source.remote.response.MoviesResponse
import com.pmaita.mov.domain.model.Movie
import com.pmaita.mov.domain.model.map.Mapper

class MovieMapper : Mapper<MoviesResponse.Result, Movie.Data>() {

    override fun map(model: MoviesResponse.Result): Movie.Data = model.run {
        Movie.Data(
            model.id,
            model.posterPath,
            model.title,
            model.voteAverage,
            model.releaseDate,
            model.overview,
            model.popularity,
            isFavorite = false
        )
    }

    override fun inverseMap(model: Movie.Data): MoviesResponse.Result {
        TODO("Not yet implemented")
    }

}