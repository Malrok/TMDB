package com.moventes.moventest.tmdb.di

import com.moventes.moventest.tmdb.fragments.MovieDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class MovieDetailFragmentModule {
    @Singleton
    @ContributesAndroidInjector
    abstract fun contributeMovieDetailFragment(): MovieDetailFragment
}