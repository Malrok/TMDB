package com.moventes.moventest.tmdb.viewmodels

import androidx.lifecycle.LiveData
import com.moventes.moventest.tmdb.models.TmdbListResult
import com.moventes.moventest.tmdb.services.TmdbNetworkService
import me.alfredobejarano.retrofitadapters.data.ApiResult
import javax.inject.Inject

class SearchMovieViewModel @Inject constructor(
    override var tmdbService: TmdbNetworkService
) : BasicViewModel(tmdbService) {

    fun searchMovie(title: String): LiveData<ApiResult<TmdbListResult>> {
        return tmdbService.getMoviesListByTitle(title)
    }

}