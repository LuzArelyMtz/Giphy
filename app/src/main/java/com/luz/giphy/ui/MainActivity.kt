package com.luz.giphy.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.luz.giphy.R
import com.luz.giphy.ui.fragment.GridViewFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*var actMainBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)*/
        /*actMainBinding.giphyViewModel = viewModel
        actMainBinding.lifecycleOwner = this*/

    }
}