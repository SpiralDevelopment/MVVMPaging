package com.spiraldev.mvvmpaging.adapters.viewholders

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.spiraldev.mvvmpaging.R
import com.spiraldev.mvvmpaging.data.local.MovieEntity
import com.spiraldev.mvvmpaging.data.remote.Api
import com.spiraldev.mvvmpaging.data.remote.vo.MovieModel
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MovieItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(movie: MovieEntity?) {
        itemView.cv_movie_title.text = movie?.title
        itemView.cv_movie_release_date.text = movie?.releaseDate

        Glide.with(itemView.context)
            .load(movie?.posterUrl)
            .placeholder(R.drawable.poster_placeholder)
            .into(itemView.cv_iv_movie_poster)
    }

    companion object {
        fun create(parent: ViewGroup): MovieItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.movie_list_item, parent, false)
            return MovieItemViewHolder(view)
        }
    }
}
