package com.moventes.moventest.tmdb.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moventes.moventest.tmdb.models.TmdbResult
import com.moventes.moventest.tmdb.services.TmdbNetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class SearchMovieViewModel @Inject constructor(
    var tmdbService: TmdbNetworkService
) : ViewModel(), Callback<TmdbResult> {

    private val result = MutableLiveData<TmdbResult>()

    fun getMovies(): LiveData<TmdbResult> {
        return result
    }

    fun searchMovie(title: String) {
        tmdbService.getMoviesListByTitle(title).enqueue(this)
    }

    override fun onFailure(call: Call<TmdbResult>, t: Throwable) {
        Timber.d(t)
    }

    override fun onResponse(call: Call<TmdbResult>, response: Response<TmdbResult>) {
        if (response.isSuccessful) {
            result.value = response.body()
        }
    }

}