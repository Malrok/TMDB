package com.moventes.moventest.tmdb.viewmodels

import android.text.format.DateFormat
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.moventes.moventest.tmdb.models.TmdbListResult
import com.moventes.moventest.tmdb.services.TmdbNetworkService
import me.alfredobejarano.retrofitadapters.data.ApiResult
import java.util.*
import javax.inject.Inject

class RecentMoviesViewModel @Inject constructor(
    var tmdbService: TmdbNetworkService
) : ViewModel() {

    private val listResult: LiveData<ApiResult<TmdbListResult>> by lazy {
        loadMovies()
    }

    fun getMovies(): LiveData<ApiResult<TmdbListResult>> {
        return listResult
    }

    private fun loadMovies(): LiveData<ApiResult<TmdbListResult>> {
        val now = Calendar.getInstance()
        val before = Calendar.getInstance()
        before.add(Calendar.DAY_OF_MONTH, -30)
        val maxDate = DateFormat.format("yyyy-MM-dd", now).toString()
        val minDate = DateFormat.format("yyyy-MM-dd", before).toString()

        return tmdbService.getRecentMovies(
            minDate, maxDate
        )
    }

}