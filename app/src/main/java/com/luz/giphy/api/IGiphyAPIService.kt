package com.luz.giphy.api

import com.luz.giphy.api.model.GiphyResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IGiphyAPIService {
    @GET("trending")
    fun getResponse(@Query("api_key") term: String = "hRfFumIWmwYhLuQ9hnD98335rCdGZ5XM"): Single<GiphyResponse>
}