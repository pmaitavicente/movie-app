package com.pmaita.mov.domain.model

data class Movie (
    val data:List<Data>,
    val message:String
)  {

    data class Data (
        val uid:Int,
        val posterPath:String?,
        val title:String,
        val voteAverage:Double,
        val releaseDate:String,
        val overview:String,
        val popularity:Double,
        val isFavorite:Boolean,
    )

}