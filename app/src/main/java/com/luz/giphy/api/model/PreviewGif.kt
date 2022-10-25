package com.luz.giphy.api.model

import com.google.gson.annotations.SerializedName

data class PreviewGif(
    @SerializedName("height")
    var height:String,

    @SerializedName("width")
    var width:String,

    @SerializedName("url")
    var url:String
)