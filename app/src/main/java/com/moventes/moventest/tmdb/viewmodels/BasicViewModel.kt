package com.moventes.moventest.tmdb.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.moventes.moventest.tmdb.models.Configuration
import com.moventes.moventest.tmdb.services.TmdbNetworkService
import me.alfredobejarano.retrofitadapters.data.ApiResult

abstract class BasicViewModel constructor(
    open var tmdbService: TmdbNetworkService
) : ViewModel() {

    private val configurationResult: LiveData<ApiResult<Configuration>> by lazy {
        loadConfiguration()
    }

    fun getConfig(): LiveData<ApiResult<Configuration>> {
        return configurationResult
    }

    private fun loadConfiguration(): LiveData<ApiResult<Configuration>> {
        return tmdbService.getConfig()
    }

}