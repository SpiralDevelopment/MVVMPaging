package com.spiraldev.mvvmpaging.data.remote

import com.spiraldev.mvvmpaging.data.remote.Endpoint.DISCOVER
import com.spiraldev.mvvmpaging.data.remote.Query.API_KEY
import com.spiraldev.mvvmpaging.data.remote.Query.API_KEY_VALUE
import com.spiraldev.mvvmpaging.data.remote.Query.INCLUDE_ADULT
import com.spiraldev.mvvmpaging.data.remote.Query.INCLUDE_ADULT_DEFAULT
import com.spiraldev.mvvmpaging.data.remote.Query.LANGUAGE
import com.spiraldev.mvvmpaging.data.remote.Query.LANGUAGE_DEFAULT
import com.spiraldev.mvvmpaging.data.remote.Query.PAGE
import com.spiraldev.mvvmpaging.data.remote.Query.SORT_BY
import com.spiraldev.mvvmpaging.data.remote.Query.SORT_BY_DEFAULT
import com.spiraldev.mvvmpaging.data.remote.vo.ResponseModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(DISCOVER)
    fun fetchMovies(@Query(PAGE) page: Int,
                    @Query(SORT_BY) sortBy: String = SORT_BY_DEFAULT,
                    @Query(LANGUAGE) language: String = LANGUAGE_DEFAULT,
                    @Query(INCLUDE_ADULT) includeAdult: Boolean = INCLUDE_ADULT_DEFAULT,
                    @Query(API_KEY) apiKey: String = API_KEY_VALUE): Single<ResponseModel>
}