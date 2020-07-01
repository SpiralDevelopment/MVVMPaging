package com.spiraldev.mvvmpaging.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.seemenstask.di.ViewModelFactory
import com.spiraldev.mvvmpaging.R
import com.spiraldev.mvvmpaging.adapters.MoviesPagedListAdapter
import com.spiraldev.mvvmpaging.data.remote.ApiService
import com.spiraldev.mvvmpaging.data.remote.NetworkState
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    val viewModel by viewModels<MainActivityViewModel> { viewModelFactory }

    lateinit var moviesAdapter: MoviesPagedListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecycler()
        initObserver()
    }

    private fun initRecycler() {
        moviesAdapter = MoviesPagedListAdapter {
            viewModel.retry()
        }

        val gridLayoutManager = GridLayoutManager(this, 4, RecyclerView.VERTICAL, false)

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val viewType = moviesAdapter.getItemViewType(position)
                if (viewType == R.layout.movie_list_item) return 1
                else return 4
            }
        }

        movies_recycler.adapter = moviesAdapter
        movies_recycler.layoutManager = gridLayoutManager
        movies_recycler.setHasFixedSize(true)
    }

    private fun initObserver() {
        viewModel.moviePagedList.observe(this, Observer {
            moviesAdapter.submitList(it)
        })

        viewModel.getNetworkState()
            .observe(this, Observer<NetworkState> {
                progress_bar_main.visibility =
                    if (viewModel.listIsEmpty() && it == NetworkState.LOADING) View.VISIBLE else View.GONE

                txt_error_main.visibility =
                    if (viewModel.listIsEmpty() && it.message != null) View.VISIBLE else View.GONE

                moviesAdapter.setNetworkState(it)
            })
    }
}