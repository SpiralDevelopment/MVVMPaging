package com.spiraldev.mvvmpaging.adapters.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spiraldev.mvvmpaging.R
import com.spiraldev.mvvmpaging.data.remote.NetworkState
import com.spiraldev.mvvmpaging.data.remote.Status
import kotlinx.android.synthetic.main.network_state_item.view.*

class NetworkStateViewHolder(val view: View, private val retryCallback: () -> Unit) :
    RecyclerView.ViewHolder(view) {

    init {
        itemView.retry_button.setOnClickListener { retryCallback() }
    }

    fun bind(networkState: NetworkState?) {

        if (networkState?.message != null) {
            itemView.error_txt.visibility = View.VISIBLE
            itemView.error_txt.text = networkState.message
        } else {
            itemView.error_txt.visibility = View.GONE
        }

        itemView.retry_button.visibility =
            if (networkState?.status == Status.FAILED) View.VISIBLE else View.GONE
        itemView.progress_bar.visibility =
            if (networkState?.status == Status.RUNNING) View.VISIBLE else View.GONE
    }

    companion object {
        fun create(parent: ViewGroup, retryCallback: () -> Unit): NetworkStateViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.network_state_item, parent, false)
            return NetworkStateViewHolder(view, retryCallback)
        }
    }

}
