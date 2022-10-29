package com.luz.giphy.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Images(
    @SerializedName("downsized_large")
    var downsized_large:DownsizedLarge,
    @SerializedName("preview_gif")
    var previewGif:PreviewGif
): Parcelable