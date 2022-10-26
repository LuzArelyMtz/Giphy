package com.luz.giphy.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.luz.giphy.R
import com.luz.giphy.api.GiphyAPIImpl
import com.luz.giphy.api.model.GiphyResponse
import com.luz.giphy.api.model.User
import com.luz.giphy.databinding.ActivityMainBinding
import com.luz.giphy.ui.adapter.GiphyGridAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers


class MainActivity : AppCompatActivity() {
    val gifAdapter by lazy{ GiphyGridAdapter() }
    lateinit var recyclerv: RecyclerView
    private lateinit var coordinatorLayout: CoordinatorLayout

    lateinit var response: GiphyResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerv = findViewById(R.id.recyclerv)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        initGridAdapter()

        var actMainBinding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        var user = User("user")
        response.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(observable: Observable?, i: Int) {
                Log.d("TA: PropertyChagedCallback", "changed")
            }
        })

       val userName: ObservableField<String>  = ObservableField()

        var userNameCallback: Observable.OnPropertyChangedCallback = object :  Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(observable: Observable?, i: Int) {
                userName.set(" your data ")
            }
        }

        userName.addOnPropertyChangedCallback(userNameCallback);



    }
    private fun handleResults(response: GiphyResponse){
        gifAdapter.submitList(response.gifList)
    }

    private fun handleError(t: Throwable){

        val snackbar = Snackbar
            .make(coordinatorLayout, "ERROR IN FETCHING API RESPONSE. Try again", Snackbar.LENGTH_LONG)
        snackbar.show()
    }

    private fun initGridAdapter(){
        recyclerv.layoutManager= GridLayoutManager(this,2)
        recyclerv.adapter=gifAdapter
    }
}