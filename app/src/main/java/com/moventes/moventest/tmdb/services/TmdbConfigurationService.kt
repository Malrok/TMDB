package com.moventes.moventest.tmdb.services

import androidx.lifecycle.LiveData
import com.moventes.moventest.tmdb.models.Configuration
import me.alfredobejarano.retrofitadapters.data.ApiResult
import javax.inject.Singleton

@Singleton
class TmdbConfigurationService {

    lateinit var configuration: LiveData<ApiResult<Configuration>>

}