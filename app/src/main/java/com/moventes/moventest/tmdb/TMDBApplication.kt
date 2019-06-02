package com.moventes.moventest.tmdb

import android.app.Activity
import android.app.Application
import android.os.StrictMode
import com.moventes.moventest.tmdb.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class TMDBApplication : Application( ), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidActivityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        if (BuildConfig.DEBUG) {
            initializeDebugConfiguration()
        }

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)

        super.onCreate()
    }

    private fun initializeDebugConfiguration() {
        Timber.plant(Timber.DebugTree())
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
            .detectDiskReads()
            .detectDiskWrites()
            .detectAll()
            .penaltyLog()
            .build())
        StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
            .detectLeakedSqlLiteObjects()
            .detectLeakedClosableObjects()
            .penaltyLog()
            .penaltyDeath()
            .build())
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidActivityInjector
    }

}