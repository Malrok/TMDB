package com.moventes.moventest.tmdb.di

import android.app.Application
import com.moventes.moventest.tmdb.TMDBApplication
import com.moventes.moventest.tmdb.network.TmdbService
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
        (RetrofitModule::class)
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun getTmdbService(): TmdbService

    fun inject(tmdbApp: TMDBApplication)
}