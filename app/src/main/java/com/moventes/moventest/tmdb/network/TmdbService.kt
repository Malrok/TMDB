package com.moventes.moventest.tmdb.network

import com.moventes.moventest.tmdb.models.Configuration
import com.moventes.moventest.tmdb.models.Movie
import com.moventes.moventest.tmdb.models.TmdbResult
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface TmdbService {

    @GET("configuration")
    fun getConfig(): Call<Configuration>

    @GET("discover/movie")
    fun getRecentMovies(@Query("primary_release_date.gte") minDate: String, @Query("primary_release_date.lte") maxDate: String): Call<TmdbResult>

}