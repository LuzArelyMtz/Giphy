package com.luz.giphy.api.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("display_name")
    var displayName:String
)