package com.moventes.moventest.tmdb.di

import com.moventes.moventest.tmdb.fragments.RecentMoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class RecentMoviesFragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeRecentMoviesFragment(): RecentMoviesFragment
}