package com.moventes.moventest.tmdb.network

import androidx.lifecycle.LiveData
import com.moventes.moventest.tmdb.models.Configuration
import com.moventes.moventest.tmdb.models.TmdbResult
import me.alfredobejarano.retrofitadapters.data.ApiResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface TmdbService {

    @GET("configuration")
    fun getConfig(): LiveData<ApiResult<Configuration>>

    @GET("discover/movie")
    fun getRecentMovies(@Query("primary_release_date.gte") minDate: String, @Query("primary_release_date.lte") maxDate: String): LiveData<ApiResult<TmdbResult>>

    @GET("search/movie")
    fun getMoviesListByTitle(@Query("query") title: String): Call<TmdbResult>
}