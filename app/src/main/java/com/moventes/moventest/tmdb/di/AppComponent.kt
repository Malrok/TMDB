package com.moventes.moventest.tmdb.di

import android.app.Application
import com.moventes.moventest.tmdb.TMDBApplication
import com.moventes.moventest.tmdb.services.TmdbNetworkService
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

@ApplicationScope
@Component(
    modules = [
        (AndroidInjectionModule::class),
        (AppModule::class),
        (MainActivityModule::class),
        (RecentMoviesFragmentModule::class),
        (SearchMovieFragmentModule::class),
        (MovieDetailFragmentModule::class),
        (RetrofitModule::class),
        (TmdbConfigurationModule::class)
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun getTmdbService(): TmdbNetworkService

    fun inject(tmdbApp: TMDBApplication)
}