package com.luz.giphy.api

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GiphyAPIImpl{
    var URLBASE="https://api.giphy.com/v1/gifs/"

    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(URLBASE)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
     fun getGiphyService()= provideRetrofit().create(IGiphyAPIService::class.java)
}