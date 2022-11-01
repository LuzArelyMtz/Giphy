package com.luz.giphy.repository

import com.luz.giphy.api.model.GiphyResponse
import io.reactivex.rxjava3.core.Single

interface Repository {
    suspend fun giphyData(): GiphyResponse
}