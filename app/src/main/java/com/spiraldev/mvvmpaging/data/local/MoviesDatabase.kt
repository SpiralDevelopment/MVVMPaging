package com.spiraldev.mvvmpaging.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.spiraldev.mvvmpaging.data.local.DB.DATABASE_NAME
import com.spiraldev.mvvmpaging.data.local.DB.DATABASE_VERSION


@Database(entities = [MovieEntity::class], version = DATABASE_VERSION, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

    companion object {
        fun buildDatabase(context: Context): MoviesDatabase {
            return Room.databaseBuilder(context, MoviesDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }
}