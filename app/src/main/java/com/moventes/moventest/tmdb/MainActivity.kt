package com.moventes.moventest.tmdb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.moventes.moventest.tmdb.fragments.recentmovies.RecentMoviesFragment
import com.moventes.moventest.tmdb.models.Movie
import com.moventes.moventest.tmdb.network.TmdbService
import dagger.android.support.AndroidSupportInjection
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), RecentMoviesFragment.OnListFragmentInteractionListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onListFragmentInteraction(movie: Movie) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
