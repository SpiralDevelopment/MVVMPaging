package com.spiraldev.mvvmpaging.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String,
    val popularity: Double,
    val voteAverage: Double,
    val posterUrl: String,
    val releaseDate: String
)