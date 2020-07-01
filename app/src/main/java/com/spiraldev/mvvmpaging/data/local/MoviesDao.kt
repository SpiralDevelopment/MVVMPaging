package com.spiraldev.mvvmpaging.data.local

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieList: List<MovieEntity>)

    @Query("SELECT * FROM movie ORDER BY id")
    fun allMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT COUNT(*) FROM Movie")
    fun getCount(): Int
}
