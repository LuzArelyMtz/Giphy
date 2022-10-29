package com.luz.giphy.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    @SerializedName("rating")
    var rating:String,

    @SerializedName("images")
    var images:Images,

    @SerializedName("user")
    var user:User
): Parcelable