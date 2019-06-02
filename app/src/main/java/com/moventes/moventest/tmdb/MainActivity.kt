package com.moventes.moventest.tmdb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.moventes.moventest.tmdb.fragments.recentmovies.RecentMoviesFragment
import com.moventes.moventest.tmdb.models.Movie
import com.moventes.moventest.tmdb.network.TmdbService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), RecentMoviesFragment.OnListFragmentInteractionListener {


//    lateinit var tmdbService: TmdbService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val httpClientBuilder = OkHttpClient.Builder()

//        val interceptor = HttpLoggingInterceptor()
//        interceptor.level = HttpLoggingInterceptor.Level.BODY
//
//        httpClientBuilder.addInterceptor(interceptor)
//        httpClientBuilder.addInterceptor { chain ->
//            val original = chain.request()
//            val originalHttpUrl = original.url()
//
//            val url = originalHttpUrl.newBuilder()
//                .addQueryParameter("api_key", "48d02d2803f669be5643367e3307dd43")
//                .build()
//
//            // Request customization: add request headers
//            val requestBuilder = original.newBuilder()
//                .url(url)
//
//            val request = requestBuilder.build()
//            chain.proceed(request)
//        }
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://api.themoviedb.org/3/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(httpClientBuilder.build())
//            .build()
//
//        tmdbService = retrofit.create<TmdbService>(TmdbService::class.java)

//        val now = Calendar.getInstance()
//        val before = Calendar.getInstance()
//        before.add(Calendar.DAY_OF_MONTH, -30)
//        val maxDate = DateFormat.format("yyyy-MM-dd", now).toString()
//        val minDate = DateFormat.format("yyyy-MM-dd", before).toString()
//
//        tmdbService.getRecentMovies(
//            maxDate,
//            minDate
//        )?.enqueue(this)
    }

    override fun onListFragmentInteraction(movie: Movie) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

//    override fun onFailure(call: Call<TmdbResult>, t: Throwable) {
//        Log.d("onFailure", t.message)
//    }
//
//    override fun onResponse(call: Call<TmdbResult>, response: Response<TmdbResult>) {
//        Log.d("onResponse", response.message())
//    }
}
