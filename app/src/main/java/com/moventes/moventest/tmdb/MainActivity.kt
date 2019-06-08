package com.moventes.moventest.tmdb

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.moventes.moventest.tmdb.fragments.OnListFragmentInteractionListener
import com.moventes.moventest.tmdb.fragments.recentmovies.RecentMoviesFragment
import com.moventes.moventest.tmdb.models.Movie
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), OnListFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        bottomNavigationView.setOnNavigationItemSelectedListener(this)
//        supportFragmentManager.beginTransaction().add(frame.id, RecentMoviesFragment()).commit()
        bottomNavigationView.setupWithNavController(Navigation.findNavController(this, R.id.nav_host_fragment))
    }

//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        Snackbar.make(this.coordinatorLayout, item.itemId.toString(), Snackbar.LENGTH_SHORT).show()
//
//        return true
//    }

    override fun onListFragmentInteraction(movie: Movie) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
