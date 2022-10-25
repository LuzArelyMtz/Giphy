package com.luz.giphy.api.model

import com.google.gson.annotations.SerializedName

data class Images(
    @SerializedName("downsized_large")
    var downsized_large:DownsizedLarge,
    @SerializedName("preview_gif")
    var previewGif:PreviewGif
)