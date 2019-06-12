package com.moventes.moventest.tmdb.viewmodels

import android.text.format.DateFormat
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.moventes.moventest.tmdb.models.TmdbResult
import com.moventes.moventest.tmdb.network.TmdbService
import me.alfredobejarano.retrofitadapters.data.ApiResult
import java.util.*
import javax.inject.Inject

class RecentMoviesViewModel @Inject constructor(
    val tmdbService: TmdbService
) : ViewModel() {

    fun getMovies(): LiveData<ApiResult<TmdbResult>> {
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