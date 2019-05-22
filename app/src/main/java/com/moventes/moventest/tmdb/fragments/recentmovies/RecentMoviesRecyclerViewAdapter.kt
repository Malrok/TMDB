package com.moventes.moventest.tmdb.fragments.recentmovies


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moventes.moventest.tmdb.R
import com.moventes.moventest.tmdb.fragments.recentmovies.RecentMoviesFragment.OnListFragmentInteractionListener
import com.moventes.moventest.tmdb.models.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_recent_movie_item.view.*

class RecentMoviesRecyclerViewAdapter(
    private val movies: List<Movie>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<RecentMoviesRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Movie
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_recent_movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movies[position]
        holder.title.text = item.title
        holder.description.text = item.overview

        Picasso.get().load(item.posterPath).into(holder.poster)

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = movies.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val poster: ImageView = mView.poster
        val title: TextView = mView.title
        val description: TextView = mView.description

        override fun toString(): String {
            return super.toString() + " '" + title.text + "'"
        }
    }
}
