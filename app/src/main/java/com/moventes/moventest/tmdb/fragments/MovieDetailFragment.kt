package com.moventes.moventest.tmdb.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.moventes.moventest.tmdb.models.TmdbResult
import com.moventes.moventest.tmdb.viewmodels.MovieDetailViewModel
import me.alfredobejarano.retrofitadapters.data.ApiResult
import javax.inject.Inject

class MovieDetailFragment : DaggeredFragment() {

    val args: MovieDetailFragmentArgs by navArgs()

    @Inject
    lateinit var viewmodel: MovieDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            com.moventes.moventest.tmdb.R.layout.fragment_movie_detail,
            container,
            false
        )
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieId = args.movieId
        viewmodel.getMovie(movieId)
            .observe(this@MovieDetailFragment, Observer<ApiResult<TmdbResult>> { result ->
                {

                }
            })
    }

}