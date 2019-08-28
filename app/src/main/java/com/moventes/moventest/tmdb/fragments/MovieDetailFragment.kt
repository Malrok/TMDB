package com.moventes.moventest.tmdb.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.moventes.moventest.tmdb.models.Configuration
import com.moventes.moventest.tmdb.models.Movie
import com.moventes.moventest.tmdb.models.TmdbResult
import com.moventes.moventest.tmdb.services.TmdbConfigurationService
import com.moventes.moventest.tmdb.tools.CombinedLiveData2
import com.moventes.moventest.tmdb.viewmodels.MovieDetailViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import me.alfredobejarano.retrofitadapters.data.ApiResult
import javax.inject.Inject

class MovieDetailFragment : DaggeredFragment() {

    val args: MovieDetailFragmentArgs by navArgs()

    @Inject
    lateinit var viewmodel: MovieDetailViewModel

    @Inject
    lateinit var tmdbConfigurationService: TmdbConfigurationService

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
        CombinedLiveData2(tmdbConfigurationService.configuration, viewmodel.getMovie(movieId))
            .observe(
                this@MovieDetailFragment,
                Observer<Pair<ApiResult<Configuration>, ApiResult<Movie>>> { result ->
                run {
                    if (result.first.error != null) {
                        return@Observer
                    }
                    if (result.second.error != null) {
                        return@Observer
                    }
                    val movie = result.second.body!!
                    title.text = movie.title
                    description.text = movie.overview

                    val posterPath = "${result.first.body!!.imageBaseUrl}${result.first.body!!.posterSizes[0]}${movie.posterPath}"

                    Picasso.get().load(posterPath).into(poster)
                }
            })
    }

}