package com.luz.giphy.api.model

import com.google.gson.annotations.SerializedName

data class GiphyResponse(
    @SerializedName("data")
    val gifList: List<Data>
)