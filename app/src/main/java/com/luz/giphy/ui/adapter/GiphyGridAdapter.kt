package com.luz.giphy.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.luz.giphy.R
import com.luz.giphy.api.model.Data

class GiphyGridAdapter(private val onItemClickListener: OnItemClickListener) :
    ListAdapter<Data, RecyclerView.ViewHolder>(GiphyGridDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GiphyGridViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.gif_grid_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GiphyGridViewHolder).bindView(onItemClickListener, getItem(position))
    }
}