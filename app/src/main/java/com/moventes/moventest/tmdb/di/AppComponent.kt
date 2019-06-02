package com.moventes.moventest.tmdb.di

import android.app.Application
import android.content.Context
import com.moventes.moventest.tmdb.TMDBApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (AndroidInjectionModule::class),
    (AppModule::class),
    (MainActivityModule::class)
])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder


        fun build(): AppComponent
    }

    fun inject(tmdbApp: TMDBApplication)
}