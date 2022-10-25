package com.luz.giphy.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GiphyAPIImpl{
    var URLBASE="https://api.giphy.com/v1/gifs/"

    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(URLBASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
     fun getGiphyService()= provideRetrofit().create(IGiphyAPIService::class.java)
}