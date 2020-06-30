package com.spiraldev.mvvmpaging.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.spiraldev.mvvmpaging.data.remote.ApiService
import com.spiraldev.mvvmpaging.data.remote.NetworkState
import com.spiraldev.mvvmpaging.data.remote.POST_PER_PAGE
import com.spiraldev.mvvmpaging.data.remote.datasources.MovieDataSource
import com.spiraldev.mvvmpaging.data.remote.datasources.MovieDataSourceFactory
import com.spiraldev.mvvmpaging.data.remote.vo.MovieModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {

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

    fun listIsEmpty(): Boolean {
        return moviePagedList.value?.isEmpty() ?: true
    }

    fun retry() {
        moviesDataSourceFactory.moviesLiveDataSource.value!!.retry()
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap<MovieDataSource, NetworkState>(
            moviesDataSourceFactory.moviesLiveDataSource, MovieDataSource::networkState)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}