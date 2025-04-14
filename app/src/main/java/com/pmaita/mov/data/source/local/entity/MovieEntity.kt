package com.pmaita.mov.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
class MovieEntity (
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "poster_path") val posterPath: String?,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "vote_average") val voteAverage: Double,
    @ColumnInfo(name = "release_date") val releaseDate: String,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "popularity") val popularity:Double,
    @ColumnInfo(name = "favorite") val isFavorite:Boolean = false
)