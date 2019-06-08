package com.moventes.moventest.tmdb.di

import com.moventes.moventest.tmdb.fragments.SearchMovieFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SearchMovieFragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeSearchMovieFragment(): SearchMovieFragment
}