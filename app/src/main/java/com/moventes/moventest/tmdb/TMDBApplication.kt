package com.moventes.moventest.tmdb

import android.app.Activity
import android.app.Application
import android.os.StrictMode
import androidx.fragment.app.Fragment
import com.moventes.moventest.tmdb.di.DaggerAppComponent
import com.moventes.moventest.tmdb.models.Configuration
import com.moventes.moventest.tmdb.services.TmdbConfigurationService
import com.moventes.moventest.tmdb.services.TmdbNetworkService
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class TMDBApplication : Application(), HasActivityInjector, HasSupportFragmentInjector, Callback<Configuration> {
    @Inject
    lateinit var dispatchingAndroidActivityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingAndroidFragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var tmdbNetworkService: TmdbNetworkService

    @Inject
    lateinit var tmdbConfigurationService: TmdbConfigurationService

    override fun onCreate() {
        if (BuildConfig.DEBUG) {
            initializeDebugConfiguration()
        }

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)

//        tmdbNetworkService.getConfig().enqueue(this)
        tmdbConfigurationService.configuration = tmdbNetworkService.getConfig()

        super.onCreate()
    }

    private fun initializeDebugConfiguration() {
        Timber.plant(Timber.DebugTree())
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectAll()
                .penaltyLog()
                .build()
        )
        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .penaltyDeath()
                .build()
        )
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidActivityInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingAndroidFragmentInjector
    }

    override fun onFailure(call: Call<Configuration>, t: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onResponse(call: Call<Configuration>, response: Response<Configuration>) {
//        this.tmdbConfigurationService.configuration = response.body()!!
    }

}