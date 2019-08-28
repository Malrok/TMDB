package com.moventes.moventest.tmdb.services

import androidx.lifecycle.LiveData
import com.moventes.moventest.tmdb.models.Configuration
import com.moventes.moventest.tmdb.models.Movie
import com.moventes.moventest.tmdb.models.TmdbListResult
import com.moventes.moventest.tmdb.models.TmdbResult
import me.alfredobejarano.retrofitadapters.data.ApiResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TmdbNetworkService {

    @GET("configuration")
    fun getConfig(): LiveData<ApiResult<Configuration>>

    @GET("discover/movie")
    fun getRecentMovies(@Query("primary_release_date.gte") minDate: String, @Query("primary_release_date.lte") maxDate: String): LiveData<ApiResult<TmdbListResult>>

    @GET("search/movie")
    fun getMoviesListByTitle(@Query("query") title: String): LiveData<ApiResult<TmdbListResult>>

    @GET("movie/{movieId}")
    fun getMovieById(@Path("movieId") movieId: String): LiveData<ApiResult<Movie>>
}