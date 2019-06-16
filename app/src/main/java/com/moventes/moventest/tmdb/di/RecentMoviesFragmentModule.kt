package com.moventes.moventest.tmdb.di

import com.moventes.moventest.tmdb.fragments.RecentMoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class RecentMoviesFragmentModule {
    @Singleton
    @ContributesAndroidInjector
    abstract fun contributeRecentMoviesFragment(): RecentMoviesFragment
}