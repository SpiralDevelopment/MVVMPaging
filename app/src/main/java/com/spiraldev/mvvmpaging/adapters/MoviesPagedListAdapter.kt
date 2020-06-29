package com.spiraldev.mvvmpaging.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.spiraldev.mvvmpaging.R
import com.spiraldev.mvvmpaging.data.remote.Api.IMAGES_URL
import com.spiraldev.mvvmpaging.data.remote.vo.MovieModel
import kotlinx.android.synthetic.main.movie_list_item.view.*


class MoviesPagedListAdapter(val context: Context) :
    PagedListAdapter<MovieModel, MoviesPagedListAdapter.MovieItemViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.movie_list_item, parent, false)
        return MovieItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bind(getItem(position), context)
    }

    class MovieDiffCallback : DiffUtil.ItemCallback<MovieModel>() {
        override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
            return oldItem == newItem
        }
    }


    class MovieItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: MovieModel?, context: Context) {
            itemView.cv_movie_title.text = movie?.title
            itemView.cv_movie_release_date.text = movie?.releaseDate

            val moviePosterURL = IMAGES_URL + movie?.posterPath
            Glide.with(itemView.context)
                .load(moviePosterURL)
                .into(itemView.cv_iv_movie_poster);
        }
    }
}