package com.spiraldev.mvvmpaging.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.spiraldev.mvvmpaging.data.local.MovieEntity
import com.spiraldev.mvvmpaging.data.local.MoviesDatabase
import com.spiraldev.mvvmpaging.data.remote.ApiService
import com.spiraldev.mvvmpaging.data.remote.NetworkState
import com.spiraldev.mvvmpaging.data.remote.POST_PER_PAGE
import com.spiraldev.mvvmpaging.data.remote.datasources.PagedListMovieBoundaryCallback
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainActivityViewModel @ViewModelInject constructor(
    private val apiService: ApiService,
    private val moviesDb: MoviesDatabase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    var moviePagedList: LiveData<PagedList<MovieEntity>>

    private val boundaryCallback: PagedListMovieBoundaryCallback =
        PagedListMovieBoundaryCallback(
            apiService,
            moviesDb,
            compositeDisposable
        )

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(POST_PER_PAGE)
            .setEnablePlaceholders(false)
            .build()

        moviePagedList =
            LivePagedListBuilder(moviesDb.moviesDao().allMovies(), config)
                .setBoundaryCallback(boundaryCallback)
                .build()
    }

    fun listIsEmpty(): Boolean {
        return moviePagedList.value?.isEmpty() ?: true
    }

    fun retry() {
        boundaryCallback.retry()
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return boundaryCallback.networkState
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}