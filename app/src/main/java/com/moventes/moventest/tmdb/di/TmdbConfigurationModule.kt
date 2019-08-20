package com.moventes.moventest.tmdb.di

import com.moventes.moventest.tmdb.services.TmdbConfigurationService
import dagger.Module
import dagger.Provides

@Module
class TmdbConfigurationModule {

    @Provides
    @ApplicationScope
    fun getTmdbConfigurationService(): TmdbConfigurationService {
        return TmdbConfigurationService()
    }
}