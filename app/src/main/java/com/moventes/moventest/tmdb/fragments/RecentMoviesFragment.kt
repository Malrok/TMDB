package com.moventes.moventest.tmdb.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moventes.moventest.tmdb.MainActivity
import com.moventes.moventest.tmdb.adapters.MoviesRecyclerViewAdapter
import com.moventes.moventest.tmdb.models.Configuration
import com.moventes.moventest.tmdb.models.TmdbListResult
import com.moventes.moventest.tmdb.services.TmdbConfigurationService
import com.moventes.moventest.tmdb.tools.CombinedLiveData2
import com.moventes.moventest.tmdb.viewmodels.RecentMoviesViewModel
import me.alfredobejarano.retrofitadapters.data.ApiResult
import javax.inject.Inject

class RecentMoviesFragment : DaggeredFragment() {

    private var listener: OnListFragmentInteractionListener? = null
    private lateinit var recycler: RecyclerView

    @Inject
    lateinit var viewmodel: RecentMoviesViewModel

    @Inject
    lateinit var tmdbConfigurationService: TmdbConfigurationService

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
        val view = inflater.inflate(
            com.moventes.moventest.tmdb.R.layout.fragment_recent_movies_list,
            container,
            false
        )

        // Set the adapter
        if (view is RecyclerView) {
            recycler = view

            with(recycler) {
                layoutManager = LinearLayoutManager(context)

                CombinedLiveData2(tmdbConfigurationService.configuration, viewmodel.getMovies())
                    .observe(
                        this@RecentMoviesFragment,
                        Observer<Pair<ApiResult<Configuration>, ApiResult<TmdbListResult>>> { result ->
                            run {
                                if (result.first.error != null) {
                                    return@Observer
                                }
                                if (result.second.error != null) {
                                    return@Observer
                                }
                                adapter = MoviesRecyclerViewAdapter(
                                    context,
                                    emptyList(),
                                    listener,
                                    result.first.body!!
                                )
                                if (adapter != null) {
                                    (adapter as MoviesRecyclerViewAdapter).updateMoviesList(result.second.body?.results)
                                    (adapter as MoviesRecyclerViewAdapter).notifyDataSetChanged()
                                }
                            }
                        })
            }
        }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}
