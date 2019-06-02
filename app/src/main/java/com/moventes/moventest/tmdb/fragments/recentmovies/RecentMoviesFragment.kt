package com.moventes.moventest.tmdb.fragments.recentmovies

import android.content.Context
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moventes.moventest.tmdb.MainActivity
import com.moventes.moventest.tmdb.models.Movie
import com.moventes.moventest.tmdb.models.TmdbResult
import com.moventes.moventest.tmdb.network.TmdbService
import dagger.android.support.AndroidSupportInjection
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject


class RecentMoviesFragment : Fragment(), Callback<TmdbResult> {

    private var listener: OnListFragmentInteractionListener? = null
    private lateinit var recycler: RecyclerView

    @Inject
    lateinit var tmdbService: TmdbService

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        if (context is MainActivity) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(com.moventes.moventest.tmdb.R.layout.fragment_recent_movies_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            recycler = view
            fillList()
        }
        return view
    }

    fun fillList() {

//        val httpClientBuilder = OkHttpClient.Builder()
//
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
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
//            .client(httpClientBuilder.build())
//            .build()
//
//        tmdbService = retrofit.create<TmdbService>(TmdbService::class.java)

        val now = Calendar.getInstance()
        val before = Calendar.getInstance()
        before.add(Calendar.DAY_OF_MONTH, -30)
        val maxDate = DateFormat.format("yyyy-MM-dd", now).toString()
        val minDate = DateFormat.format("yyyy-MM-dd", before).toString()

        tmdbService.getRecentMovies(
            minDate,
            maxDate
        ).enqueue(this)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onResponse(call: Call<TmdbResult>, response: Response<TmdbResult>) {
        with(recycler) {
            layoutManager = LinearLayoutManager(context)
            adapter =
                RecentMoviesRecyclerViewAdapter(
                    context,
                    response.body()!!.results,
                    listener
                )
        }
    }

    override fun onFailure(call: Call<TmdbResult>, t: Throwable) {
        Log.d("onFailure", "error")
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(movie: Movie)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            RecentMoviesFragment().apply {}
    }
}
