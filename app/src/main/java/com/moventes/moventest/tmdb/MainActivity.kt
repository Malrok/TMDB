package com.moventes.moventest.tmdb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.moventes.moventest.tmdb.fragments.BottomNavFragmentDirections
import com.moventes.moventest.tmdb.fragments.OnListFragmentInteractionListener
import com.moventes.moventest.tmdb.models.Movie
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), OnListFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onListFragmentInteraction(movie: Movie) {
        val action = BottomNavFragmentDirections.actionBottomNavFragmentToMovieDetailFagment(movie.id.toString());

        findNavController(R.id.bottomNavigationView).navigate(action)
    }

}
