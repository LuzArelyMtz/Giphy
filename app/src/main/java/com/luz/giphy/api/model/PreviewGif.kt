package com.luz.giphy.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PreviewGif(
    @SerializedName("height")
    var height:String,

    @SerializedName("width")
    var width:String,

    @SerializedName("url")
    var url:String
):Parcelable