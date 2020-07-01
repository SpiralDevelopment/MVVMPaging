package com.spiraldev.mvvmpaging.data.remote.datasources

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.spiraldev.mvvmpaging.data.local.MovieEntity
import com.spiraldev.mvvmpaging.data.local.MoviesDatabase
import com.spiraldev.mvvmpaging.data.remote.ApiService
import com.spiraldev.mvvmpaging.data.remote.NetworkState
import com.spiraldev.mvvmpaging.data.remote.POST_PER_PAGE
import com.spiraldev.mvvmpaging.data.remote.utils.PagingRequestHelper
import com.spiraldev.mvvmpaging.data.remote.vo.toMovieEntity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors

class PagedListMovieBoundaryCallback(
    private val apiService: ApiService,
    private val moviesDb: MoviesDatabase,
    val compositeDisposable: CompositeDisposable
) : PagedList.BoundaryCallback<MovieEntity>() {

    val networkState = MutableLiveData<NetworkState>()

    private var pageNum = 1
    private val executor = Executors.newSingleThreadExecutor()
    private val helper = PagingRequestHelper(executor)
    private lateinit var rType: PagingRequestHelper.RequestType

    fun retry() {
        fetchAndStoreMovies(rType)
    }

    override fun onZeroItemsLoaded() {
        fetchAndStoreMovies(PagingRequestHelper.RequestType.INITIAL)
    }

    override fun onItemAtEndLoaded(itemAtEnd: MovieEntity) {

        executor.execute {
            val page = moviesDb.moviesDao().getCount()
            pageNum = page / POST_PER_PAGE
            pageNum++
        }

        fetchAndStoreMovies(PagingRequestHelper.RequestType.AFTER)
    }

    private fun fetchAndStoreMovies(rType: PagingRequestHelper.RequestType) {
        this.rType = rType

        helper.runIfNotRunning(rType) { callback ->
            networkState.postValue(NetworkState.LOADING)

            compositeDisposable.add(apiService.fetchPopularMovies(pageNum)
                .map { response -> response.movieList.map { it.toMovieEntity() } }
                .doOnSuccess {
                    moviesDb.moviesDao().insert(it)
                }
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        networkState.postValue(NetworkState.LOADED)
                        callback.recordSuccess()
                    },
                    {
                        networkState.postValue(NetworkState.error(it.message))
                        callback.recordFailure(it)
                    }
                ))
        }
    }
}
