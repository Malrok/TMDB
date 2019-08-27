package com.moventes.moventest.tmdb.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moventes.moventest.tmdb.models.TmdbListResult
import com.moventes.moventest.tmdb.services.TmdbNetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class SearchMovieViewModel @Inject constructor(
    var tmdbService: TmdbNetworkService
) : ViewModel(), Callback<TmdbListResult> {

    private val result = MutableLiveData<TmdbListResult>()

    fun getMovies(): LiveData<TmdbListResult> {
        return result
    }

    fun searchMovie(title: String) {
        tmdbService.getMoviesListByTitle(title).enqueue(this)
    }

    override fun onFailure(call: Call<TmdbListResult>, t: Throwable) {
        Timber.d(t)
    }

    override fun onResponse(call: Call<TmdbListResult>, response: Response<TmdbListResult>) {
        if (response.isSuccessful) {
            result.value = response.body()
        }
    }

}