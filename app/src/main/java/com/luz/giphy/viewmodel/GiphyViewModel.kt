package com.luz.giphy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luz.giphy.api.GiphyAPIImpl
import com.luz.giphy.api.model.Data
import com.luz.giphy.api.model.GiphyResponse
import com.luz.giphy.repository.GiphyRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class GiphyViewModel() : ViewModel() {
    private var _livedataGiphy = MutableLiveData<List<Data>>()
    var livedataGiphy: LiveData<List<Data>> = _livedataGiphy

    private var _progressbar = MutableLiveData<Boolean>()
    val progressbar: LiveData<Boolean> = _progressbar

    private var _countryLoadError = MutableLiveData<Boolean>()
    var countryLoadError: LiveData<Boolean> = _countryLoadError

    private val service = GiphyAPIImpl()
    private val giphyRepository: GiphyRepository by lazy{GiphyRepository(service)}

    private val disposable = CompositeDisposable()

    fun getGiphyGift() {
        _progressbar.postValue(true)
        disposable.add(giphyRepository.giphyData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<GiphyResponse>() {
                override fun onSuccess(response: GiphyResponse) {
                    _livedataGiphy.value = response.gifList
                    _progressbar.value = false
                }

                override fun onError(e: Throwable) {
                    _countryLoadError.value = true
                    _progressbar.value = false
                    e.printStackTrace()
                }
            })
        )
    }
    override fun onCleared(){
        super.onCleared()
        disposable.clear()
    }
}