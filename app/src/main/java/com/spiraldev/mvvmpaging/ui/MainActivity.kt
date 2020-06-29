package com.spiraldev.mvvmpaging.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.spiraldev.mvvmpaging.R
import com.spiraldev.mvvmpaging.adapters.MoviesPagedListAdapter
import com.spiraldev.mvvmpaging.data.remote.ApiService
import com.spiraldev.mvvmpaging.data.remote.getService
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainActivityViewModel
    lateinit var moviesAdapter: MoviesPagedListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        initRecycler()
        initObserver()
    }

    private fun initRecycler() {
        moviesAdapter = MoviesPagedListAdapter(this)

        movies_recycler.setHasFixedSize(true)
        movies_recycler.adapter = moviesAdapter
    }

    private fun initViewModel() {
        val apiService: ApiService = getService()
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MainActivityViewModel(apiService) as T
            }
        }).get(MainActivityViewModel::class.java)
    }

    private fun initObserver() {
        viewModel.moviePagedList.observe(this, Observer {
            moviesAdapter.submitList(it)
        })
    }
}