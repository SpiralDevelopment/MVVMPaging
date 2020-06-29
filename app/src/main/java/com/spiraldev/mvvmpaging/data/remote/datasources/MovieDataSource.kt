package com.spiraldev.mvvmpaging.data.remote.datasources

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.spiraldev.mvvmpaging.data.remote.ApiService
import com.spiraldev.mvvmpaging.data.remote.FIRST_PAGE
import com.spiraldev.mvvmpaging.data.remote.vo.MovieModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class MovieDataSource (private val apiService : ApiService, private val compositeDisposable: CompositeDisposable)
    : PageKeyedDataSource<Int, MovieModel>() {

    private var page = FIRST_PAGE

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, MovieModel>) {
        compositeDisposable.add(
            apiService.fetchMovies(page)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        callback.onResult(it.movieList, null, page+1)
                    },
                    {
                        Log.d("TAG", it.message)
                    }
                )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieModel>) {
        compositeDisposable.add(
            apiService.fetchMovies(params.key)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        if(it.totalPages >= params.key) {
                            callback.onResult(it.movieList, params.key+1)
                        }
                    },
                    {
                        Log.d("TAG", it.message)
                    }
                )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieModel>) {

    }
}
