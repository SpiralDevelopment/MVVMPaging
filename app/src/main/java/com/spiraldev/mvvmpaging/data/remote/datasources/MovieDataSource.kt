package com.spiraldev.mvvmpaging.data.remote.datasources

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.spiraldev.mvvmpaging.data.remote.ApiService
import com.spiraldev.mvvmpaging.data.remote.FIRST_PAGE
import com.spiraldev.mvvmpaging.data.remote.NetworkState
import com.spiraldev.mvvmpaging.data.remote.vo.MovieModel
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers


class MovieDataSource(
    private val apiService: ApiService,
    private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, MovieModel>() {

    private var page = FIRST_PAGE
    val networkState = MutableLiveData<NetworkState>()
    private var retryCompletable: Completable? = null


    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(
                retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ }, { Log.d("TAG", it.message) })
            )
        }
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MovieModel>
    ) {
        networkState.postValue(NetworkState.LOADING)

        compositeDisposable.add(
            apiService.fetchMovies(page)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        callback.onResult(it.movieList, null, page + 1)
                        networkState.postValue(NetworkState.LOADED)
                        setRetry(null)
                    },
                    {
                        Log.d("TAG", it.message)
                        networkState.postValue(NetworkState.error(it.message))
                        setRetry(Action { loadInitial(params, callback) })
                    }
                )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MovieModel>) {
        networkState.postValue(NetworkState.LOADING)
        compositeDisposable.add(
            apiService.fetchMovies(params.key)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        if (it.totalPages >= params.key) {
                            callback.onResult(it.movieList, params.key + 1)
                        }

                        setRetry(null)
                        networkState.postValue(NetworkState.LOADED)
                    },
                    {
                        Log.d("TAG", it.message)
                        setRetry(Action { loadAfter(params, callback) })
                        networkState.postValue(NetworkState.error(it.message))
                    }
                )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MovieModel>) {

    }

    private fun setRetry(action: Action?) {
        if (action == null) {
            this.retryCompletable = null
        } else {
            this.retryCompletable = Completable.fromAction(action)
        }
    }
}
