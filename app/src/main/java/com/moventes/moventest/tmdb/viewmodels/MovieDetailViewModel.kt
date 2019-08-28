package com.moventes.moventest.tmdb.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.moventes.moventest.tmdb.models.Movie
import com.moventes.moventest.tmdb.services.TmdbNetworkService
import me.alfredobejarano.retrofitadapters.data.ApiResult
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    var tmdbService: TmdbNetworkService
) : ViewModel() {

    private lateinit var movieId: String

    private val result: LiveData<ApiResult<Movie>> by lazy {
        loadMovies()
    }

    fun getMovie(movieId: String): LiveData<ApiResult<Movie>> {
        this.movieId = movieId
        return result
    }

    private fun loadMovies(): LiveData<ApiResult<Movie>> {
        return tmdbService.getMovieById(
            this.movieId
        )
    }

}