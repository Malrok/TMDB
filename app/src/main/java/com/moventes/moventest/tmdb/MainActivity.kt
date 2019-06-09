package com.moventes.moventest.tmdb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import com.moventes.moventest.tmdb.fragments.OnListFragmentInteractionListener
import com.moventes.moventest.tmdb.models.Movie
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), OnListFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        bottomNavigationView.setupWithNavController(Navigation.findNavController(this, R.id.nav_host_fragment))
//        mainNavHostFragment.setup
    }

    override fun onListFragmentInteraction(movie: Movie) {
        Snackbar.make(coordinatorLayout, movie.id.toString(), Snackbar.LENGTH_SHORT).show()
    }

}
