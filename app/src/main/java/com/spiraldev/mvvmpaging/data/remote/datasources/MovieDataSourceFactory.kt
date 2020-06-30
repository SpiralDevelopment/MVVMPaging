package com.spiraldev.mvvmpaging.data.remote.datasources

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.spiraldev.mvvmpaging.data.remote.ApiService
import com.spiraldev.mvvmpaging.data.remote.vo.MovieModel
import io.reactivex.disposables.CompositeDisposable

class MovieDataSourceFactory(
    private val apiService: ApiService,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, MovieModel>() {

    val moviesLiveDataSource = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, MovieModel> {
        val movieDataSource = MovieDataSource(apiService, compositeDisposable)

        moviesLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }
}