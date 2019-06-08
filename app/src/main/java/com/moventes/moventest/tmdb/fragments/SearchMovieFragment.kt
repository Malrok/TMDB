package com.moventes.moventest.tmdb.fragments


import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moventes.moventest.tmdb.MainActivity
import com.moventes.moventest.tmdb.R
import com.moventes.moventest.tmdb.adapters.MoviesRecyclerViewAdapter
import com.moventes.moventest.tmdb.models.TmdbResult
import com.moventes.moventest.tmdb.network.TmdbService
import kotlinx.android.synthetic.main.fragment_search_movie_list.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class SearchMovieFragment : DaggeredFragment(), Callback<TmdbResult> {

    @Inject
    lateinit var tmdbService: TmdbService

    private var listener: OnListFragmentInteractionListener? = null
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: MoviesRecyclerViewAdapter

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
        val view = inflater.inflate(R.layout.fragment_search_movie_list, container, false)

        // Set the adapter
        recycler = view.list
        recycler.layoutManager = LinearLayoutManager(context)
        adapter = MoviesRecyclerViewAdapter(
            context!!,
            ArrayList(),
            listener
        )
        recycler.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.search.editText?.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                tmdbService.getMoviesListByTitle(s.toString()).enqueue(this@SearchMovieFragment)
            }

        })
    }

    override fun onResponse(call: Call<TmdbResult>, response: Response<TmdbResult>) {
        adapter.updateMoviesList(response.body()!!.results)
        adapter.notifyDataSetChanged()
    }

    override fun onFailure(call: Call<TmdbResult>, t: Throwable) {
        Timber.d("error")
    }

}
