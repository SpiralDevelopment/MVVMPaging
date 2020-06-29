package com.spiraldev.mvvmpaging.data.remote.vo

import com.google.gson.annotations.SerializedName


data class ResponseModel(
    val page: Int,
    @SerializedName("results")
    val movieList: List<MovieModel>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)