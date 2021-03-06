package com.moventes.moventest.tmdb.fragments


import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moventes.moventest.tmdb.MainActivity
import com.moventes.moventest.tmdb.R
import com.moventes.moventest.tmdb.adapters.MoviesRecyclerViewAdapter
import com.moventes.moventest.tmdb.models.Configuration
import com.moventes.moventest.tmdb.models.TmdbListResult
import com.moventes.moventest.tmdb.services.TmdbConfigurationService
import com.moventes.moventest.tmdb.services.TmdbNetworkService
import com.moventes.moventest.tmdb.tools.CombinedLiveData2
import com.moventes.moventest.tmdb.viewmodels.SearchMovieViewModel
import kotlinx.android.synthetic.main.fragment_search_movie_list.view.*
import me.alfredobejarano.retrofitadapters.data.ApiResult
import javax.inject.Inject

class SearchMovieFragment : DaggeredFragment() {

//    @Inject
//    lateinit var viewmodel: SearchMovieViewModel

    @Inject
    lateinit var tmdbConfigurationService: TmdbConfigurationService

    @Inject
    lateinit var tmdbNetworkService: TmdbNetworkService

    private var listener: OnListFragmentInteractionListener? = null
    private lateinit var recycler: RecyclerView

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

        with(recycler) {
            layoutManager = LinearLayoutManager(context)

            tmdbConfigurationService.configuration
                .observe(
                    this@SearchMovieFragment,
                    androidx.lifecycle.Observer<ApiResult<Configuration>> { result ->
                        run {
                            if (result.error != null) {
                                return@Observer
                            }
                            adapter = MoviesRecyclerViewAdapter(
                                context,
                                emptyList(),
                                listener,
                                result.body!!
                            )
                        }
                    })
        }

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
                tmdbNetworkService.getMoviesListByTitle(s.toString()).observe(
                    this@SearchMovieFragment,
                    Observer<ApiResult<TmdbListResult>> { result ->
                        run {
                            if (result.error != null) {
                                return@Observer
                            }
                            with(recycler) {
                                if (adapter != null) {
                                    (adapter as MoviesRecyclerViewAdapter).updateMoviesList(result.body!!.results)
                                    (adapter as MoviesRecyclerViewAdapter).notifyDataSetChanged()
                                }
                            }
                        }
                    }
                )
            }

        })
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

}
