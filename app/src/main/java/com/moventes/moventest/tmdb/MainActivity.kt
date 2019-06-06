package com.moventes.moventest.tmdb

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.moventes.moventest.tmdb.fragments.recentmovies.RecentMoviesFragment
import com.moventes.moventest.tmdb.models.Movie
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), RecentMoviesFragment.OnListFragmentInteractionListener,
    BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Snackbar.make(this.coordinatorLayout, item.itemId.toString(), Snackbar.LENGTH_SHORT).show()
        return true
    }

    override fun onListFragmentInteraction(movie: Movie) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
