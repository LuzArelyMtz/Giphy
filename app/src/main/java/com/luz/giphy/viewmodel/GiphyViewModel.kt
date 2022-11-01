package com.luz.giphy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luz.giphy.api.GiphyAPIImpl
import com.luz.giphy.api.model.Data
import com.luz.giphy.api.model.GiphyResponse
import com.luz.giphy.repository.GiphyRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch

class GiphyViewModel : ViewModel() {
    private var _livedataGiphy = MutableLiveData<List<Data>>()
    var livedataGiphy: LiveData<List<Data>> = _livedataGiphy

    private var _progressbar = MutableLiveData<Boolean>()
    val progressbar: LiveData<Boolean> = _progressbar

    private var _countryLoadError = MutableLiveData<Boolean>()
    var countryLoadError: LiveData<Boolean> = _countryLoadError

    private var _username = MutableLiveData<String>()
    var username: LiveData<String> = _username

    private var _rating = MutableLiveData<String>()
    var rating: LiveData<String> = _rating

    private var _imgurl = MutableLiveData<String>()
    var imgurl: LiveData<String> = _imgurl

    private val service = GiphyAPIImpl()
    private val giphyRepository: GiphyRepository by lazy { GiphyRepository(service) }


    fun getGiphyGift() {
        viewModelScope.launch {
            _progressbar.postValue(true)
            _livedataGiphy.value = giphyRepository.giphyData().gifList
        }
        _progressbar.value = false
    }

    fun setData(data: Data) {
        _username.value = data.user.displayName
        _rating.value = data.rating
        _imgurl.value = data.images.downsized_large.url
    }

    override fun onCleared() {
        super.onCleared()
    }
}