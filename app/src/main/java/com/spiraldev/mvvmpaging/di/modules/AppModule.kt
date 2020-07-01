package com.example.seemenstask.di.modules

import android.app.Application
import android.content.Context
import com.spiraldev.mvvmpaging.data.local.MoviesDao
import com.spiraldev.mvvmpaging.data.local.MoviesDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(app: Application): Context {
        return app.applicationContext
    }

    @Singleton
    @Provides
    fun providesMoviesDatabase(context: Context): MoviesDatabase =
        MoviesDatabase.buildDatabase(context)
}