package com.luz.giphy.repository

import com.luz.giphy.api.GiphyAPIImpl
import com.luz.giphy.api.model.GiphyResponse
import io.reactivex.rxjava3.core.Single

class GiphyRepository(private val giphyService: GiphyAPIImpl) : Repository {
    override suspend fun giphyData(): GiphyResponse {
        return giphyService.getGiphyService().getResponse()
    }

}