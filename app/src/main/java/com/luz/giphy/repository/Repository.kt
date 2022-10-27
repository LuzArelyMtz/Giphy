package com.luz.giphy.repository

import com.luz.giphy.api.model.GiphyResponse
import io.reactivex.rxjava3.core.Single

interface Repository {
    fun giphyData(): Single<GiphyResponse>
}