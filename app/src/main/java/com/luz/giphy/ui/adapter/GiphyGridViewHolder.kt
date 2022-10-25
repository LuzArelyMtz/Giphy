package com.luz.giphy.ui.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.luz.giphy.R
import com.luz.giphy.api.model.Data

class GiphyGridViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    val imgGiphy = itemView.findViewById<ImageView>(R.id.imgGiphy)

    fun bindView(data: Data){
        Glide.with(itemView.context).load(data.images.downsized_large.url).into(imgGiphy)

    }
}