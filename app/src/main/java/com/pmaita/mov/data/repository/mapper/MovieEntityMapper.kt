package com.pmaita.mov.data.repository.mapper

import com.pmaita.mov.data.source.local.entity.MovieEntity
import com.pmaita.mov.domain.model.Movie
import com.pmaita.mov.domain.model.map.Mapper

class MovieEntityMapper : Mapper<MovieEntity, Movie.Data>() {

    override fun map(
        model: MovieEntity
    ): Movie.Data = model.run {
        Movie.Data(
            model.uid,
            model.posterPath,
            model.title,
            model.voteAverage,
            model.releaseDate,
            model.overview,
            model.popularity,
            model.isFavorite
        )
    }

    override fun inverseMap(
        model: Movie.Data
    ): MovieEntity = model.run {
        MovieEntity(
            model.uid,
            model.posterPath,
            model.title,
            model.voteAverage,
            model.releaseDate,
            model.overview,
            model.popularity,
            model.isFavorite
        )
    }

}