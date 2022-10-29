package com.luz.giphy.ui.adapter

import android.view.View
import com.luz.giphy.api.model.Data

interface OnItemClickListener {
    fun onClick(v: View?, data: Data)
}