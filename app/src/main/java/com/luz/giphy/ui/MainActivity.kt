package com.luz.giphy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.luz.giphy.R
import com.luz.giphy.api.GiphyAPIImpl
import com.luz.giphy.api.IGiphyAPIService
import com.luz.giphy.api.model.GiphyResponse
import com.luz.giphy.ui.adapter.GiphyGridAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val gifAdapter by lazy{ GiphyGridAdapter() }
    lateinit var recyclerv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerv = findViewById(R.id.recyclerv)
        initGridAdapter()

        val giphyService:IGiphyAPIService= GiphyAPIImpl().provideRetrofit().create(IGiphyAPIService::class.java)
        val call = giphyService.getResponse()
        call.enqueue(object: Callback<GiphyResponse> {
            override fun onResponse(call: Call<GiphyResponse>, response: Response<GiphyResponse>) {
                Log.d("Giphy", response.body().toString())
                gifAdapter.submitList(response.body()?.gifList)
            }
            override fun onFailure(call: Call<GiphyResponse>, t: Throwable) {
            }
        })
    }

    private fun initGridAdapter(){
        recyclerv.layoutManager= GridLayoutManager(this,2)
        recyclerv.adapter=gifAdapter
    }
}