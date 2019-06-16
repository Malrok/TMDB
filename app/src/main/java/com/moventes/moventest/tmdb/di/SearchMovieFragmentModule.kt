package com.moventes.moventest.tmdb.di

import com.moventes.moventest.tmdb.fragments.SearchMovieFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class SearchMovieFragmentModule {
    @Singleton
    @ContributesAndroidInjector
    abstract fun contributeSearchMovieFragment(): SearchMovieFragment
}