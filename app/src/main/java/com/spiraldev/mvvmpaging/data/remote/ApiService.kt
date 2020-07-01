package com.spiraldev.mvvmpaging.data.remote

import com.spiraldev.mvvmpaging.data.remote.Query.API_KEY
import com.spiraldev.mvvmpaging.data.remote.Query.API_KEY_VALUE
import com.spiraldev.mvvmpaging.data.remote.Query.PAGE
import com.spiraldev.mvvmpaging.data.remote.vo.ResponseModel
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("3/movie/popular")
    fun fetchPopularMovies(@Query(PAGE) page: Int,
                    @Query(API_KEY) apiKey: String = API_KEY_VALUE): Single<ResponseModel>
}