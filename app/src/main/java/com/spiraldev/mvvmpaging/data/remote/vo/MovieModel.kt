package com.spiraldev.mvvmpaging.data.remote.vo

import com.google.gson.annotations.SerializedName

data class MovieModel(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("overview") val overview: String
)
