package com.spiraldev.mvvmpaging.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.spiraldev.mvvmpaging.data.remote.ApiService
import com.spiraldev.mvvmpaging.data.remote.POST_PER_PAGE
import com.spiraldev.mvvmpaging.data.remote.datasources.MovieDataSourceFactory
import com.spiraldev.mvvmpaging.data.remote.vo.MovieModel
import io.reactivex.disposables.CompositeDisposable

class MainActivityViewModel(private val apiService: ApiService) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private var moviesDataSourceFactory: MovieDataSourceFactory

    var moviePagedList: LiveData<PagedList<MovieModel>>

    init {
        moviesDataSourceFactory = MovieDataSourceFactory(apiService, compositeDisposable)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()

        moviePagedList = LivePagedListBuilder(moviesDataSourceFactory, config).build()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}