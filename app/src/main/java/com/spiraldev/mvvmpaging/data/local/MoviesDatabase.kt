package com.spiraldev.mvvmpaging.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

    companion object {
        private const val databaseName = "Movies.DB"

        fun buildDatabase(context: Context): MoviesDatabase {
            return Room.databaseBuilder(context, MoviesDatabase::class.java, databaseName)
                .build()
        }
    }
}