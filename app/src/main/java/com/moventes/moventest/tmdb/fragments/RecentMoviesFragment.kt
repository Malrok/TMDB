package com.moventes.moventest.tmdb.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MediatorLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moventes.moventest.tmdb.MainActivity
import com.moventes.moventest.tmdb.adapters.MoviesRecyclerViewAdapter
import com.moventes.moventest.tmdb.models.Configuration
import com.moventes.moventest.tmdb.models.TmdbResult
import com.moventes.moventest.tmdb.services.TmdbConfigurationService
import com.moventes.moventest.tmdb.tools.combineWith
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
        val view = inflater.inflate(com.moventes.moventest.tmdb.R.layout.fragment_recent_movies_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            recycler = view

            // TODO:
            //      enchainer les livedata "genre" pipe ?!
            //      trouver pourquoi la configuration revient à vide

//            with(recycler) {
//                layoutManager = LinearLayoutManager(context)
//                tmdbConfigurationService.configuration.observe(this@RecentMoviesFragment, androidx.lifecycle.Observer<ApiResult<Configuration>> { result ->
//                    run {
//                        if (result.error != null) {
//
//                        } else {
//                            if (result.body?.imageBaseUrl != null) {
//                                adapter = MoviesRecyclerViewAdapter(
//                                    context,
//                                    emptyList(),
//                                    listener,
//                                    result.body!!
//                                )
//                            }
//                        }
//                    }
//                })
//            }
//            viewmodel.getMovies().observe(this, androidx.lifecycle.Observer<ApiResult<TmdbResult>> { result ->
//                run {
//                    if (result.error != null) {
//
//                    } else {
//                        with(recycler) {
//                            if (adapter != null) {
//                                (adapter as MoviesRecyclerViewAdapter).updateMoviesList(result.body?.results)
//                                (adapter as MoviesRecyclerViewAdapter).notifyDataSetChanged()
//                            }
//                        }
//                    }
//                }
//            })

            with(recycler) {
                layoutManager = LinearLayoutManager(context)

                tmdbConfigurationService.configuration.combineWith(viewmodel.getMovies()) { configurationResult, moviesResult ->
                    if (configurationResult?.error != null) {

                    }
                    if (moviesResult?.error != null) {

                    }
                    adapter = MoviesRecyclerViewAdapter(
                        context,
                        emptyList(),
                        listener,
                        configurationResult?.body!!
                    )
                    if (adapter != null) {
                        (adapter as MoviesRecyclerViewAdapter).updateMoviesList(moviesResult?.body?.results)
                        (adapter as MoviesRecyclerViewAdapter).notifyDataSetChanged()
                    }
                }

//                tmdbConfigurationService.configuration.observe(this@RecentMoviesFragment, androidx.lifecycle.Observer<ApiResult<Configuration>> { result ->
//                    run {
//                        if (result.error != null) {
//
//                        } else {
//                            if (result.body?.imageBaseUrl != null) {
//                                adapter = MoviesRecyclerViewAdapter(
//                                    context,
//                                    emptyList(),
//                                    listener,
//                                    result.body!!
//                                )
//                            }
//                        }
//                    }
//                })
            }
//            viewmodel.getMovies().observe(this, androidx.lifecycle.Observer<ApiResult<TmdbResult>> { result ->
//                run {
//                    if (result.error != null) {
//
//                    } else {
//                        with(recycler) {
//                            if (adapter != null) {
//                                (adapter as MoviesRecyclerViewAdapter).updateMoviesList(result.body?.results)
//                                (adapter as MoviesRecyclerViewAdapter).notifyDataSetChanged()
//                            }
//                        }
//                    }
//                }
//            })
        }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}
