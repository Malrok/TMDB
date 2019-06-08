package com.moventes.moventest.tmdb.fragments.searchmovie


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moventes.moventest.tmdb.MainActivity
import com.moventes.moventest.tmdb.R
import com.moventes.moventest.tmdb.fragments.DaggeredFragment
import com.moventes.moventest.tmdb.fragments.OnListFragmentInteractionListener
import com.moventes.moventest.tmdb.network.TmdbService
import javax.inject.Inject

class SearchMovieFragment : DaggeredFragment() {

    @Inject
    lateinit var tmdbService: TmdbService

    private var listener: OnListFragmentInteractionListener? = null

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_movie_list, container, false)
    }

}
