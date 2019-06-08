package com.moventes.moventest.tmdb.fragments.recentmovies

import android.content.Context
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moventes.moventest.tmdb.MainActivity
import com.moventes.moventest.tmdb.adapters.MoviesRecyclerViewAdapter
import com.moventes.moventest.tmdb.fragments.DaggeredFragment
import com.moventes.moventest.tmdb.fragments.OnListFragmentInteractionListener
import com.moventes.moventest.tmdb.models.TmdbResult
import com.moventes.moventest.tmdb.network.TmdbService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.util.*
import javax.inject.Inject


class RecentMoviesFragment : DaggeredFragment(), Callback<TmdbResult> {

    @Inject
    lateinit var tmdbService: TmdbService

    private var listener: OnListFragmentInteractionListener? = null
    private lateinit var recycler: RecyclerView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnListFragmentInteractionListener")
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
                MoviesRecyclerViewAdapter(
                    context,
                    response.body()!!.results,
                    listener
                )
        }
    }

    override fun onFailure(call: Call<TmdbResult>, t: Throwable) {
        Timber.d("error")
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            RecentMoviesFragment().apply {}
    }
}
