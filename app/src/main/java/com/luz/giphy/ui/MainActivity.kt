package com.luz.giphy.ui

import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.luz.giphy.R
import com.luz.giphy.api.model.Data
import com.luz.giphy.api.model.GiphyResponse
import com.luz.giphy.databinding.ActivityMainBinding
import com.luz.giphy.ui.adapter.GiphyGridAdapter
import com.luz.giphy.viewmodel.GiphyViewModel


class MainActivity : AppCompatActivity() {
    val gifAdapter by lazy { GiphyGridAdapter() }
    lateinit var recyclerv: RecyclerView
    private lateinit var coordinatorLayout: CoordinatorLayout
    private val progressbar: ProgressBar? = null
    //private val refreshLayout: SwipeRefreshLayout? = null
    //private val viewModel: GiphyViewModel by viewModels()
    private lateinit var viewModel: GiphyViewModel

    lateinit var response: GiphyResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*var actMainBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)*/
        recyclerv = findViewById(R.id.recyclerv)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        initGridAdapter()

        viewModel = ViewModelProvider(this)[GiphyViewModel::class.java]
        /*actMainBinding.giphyViewModel = viewModel
        actMainBinding.lifecycleOwner = this*/
        viewModel.getGiphyGift()
        viewModel.livedataGiphy.observe(this, Observer {
            handleResults(it)
        })
    }

    private fun handleResults(giftList: List<Data>) {
        gifAdapter.submitList(giftList)
    }

    private fun handleError(t: Throwable) {
        val snackbar = Snackbar
            .make(
                coordinatorLayout,
                "ERROR IN FETCHING API RESPONSE. Try again",
                Snackbar.LENGTH_LONG
            )
        snackbar.show()
    }

    private fun initGridAdapter() {
        recyclerv.layoutManager = GridLayoutManager(this, 2)
        recyclerv.adapter = gifAdapter
    }
}