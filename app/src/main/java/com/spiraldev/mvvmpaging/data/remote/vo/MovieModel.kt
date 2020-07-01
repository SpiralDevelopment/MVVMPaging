package com.spiraldev.mvvmpaging.data.remote.vo

import com.google.gson.annotations.SerializedName
import com.spiraldev.mvvmpaging.data.local.MovieEntity
import com.spiraldev.mvvmpaging.data.remote.Api.IMAGES_URL

data class MovieModel(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String
)

fun MovieModel.toMovieEntity() =
    MovieEntity(0, title, popularity, voteAverage, getPosterURL(posterPath), releaseDate)

private fun getPosterURL(posterPath: String) = IMAGES_URL + posterPath
