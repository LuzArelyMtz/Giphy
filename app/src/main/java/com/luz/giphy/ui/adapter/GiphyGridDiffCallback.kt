package com.luz.giphy.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.luz.giphy.api.model.Data

class GiphyGridDiffCallback:DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data)= oldItem.images.downsized_large.url== newItem.images.downsized_large.url

    override fun areContentsTheSame(oldItem: Data, newItem: Data)=oldItem==newItem
}