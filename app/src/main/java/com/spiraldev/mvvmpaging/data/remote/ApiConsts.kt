package com.spiraldev.mvvmpaging.data.remote

const val FIRST_PAGE = 1
const val POST_PER_PAGE = 20

object Api {
    const val THE_MOVIE_URL = "https://api.themoviedb.org/3/"
    const val IMAGES_URL = "https://image.tmdb.org/t/p/w185_and_h278_bestv2"
}

object Endpoint {
    const val DISCOVER = "discover/movie"
}

object Query {
    const val API_KEY = "api_key"
    const val PAGE = "page"
    const val SORT_BY = "sort_by"
    const val LANGUAGE = "language"
    const val INCLUDE_ADULT = "include_adult"

    const val SORT_BY_DEFAULT = "popularity.desc"
    const val LANGUAGE_DEFAULT = "en-US"
    const val INCLUDE_ADULT_DEFAULT = false

    const val API_KEY_VALUE = "73600704c1c70585df13771486247174"
}
