package com.luz.giphy.viewmodel

import android.util.Log
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.ViewModel
import com.luz.giphy.api.GiphyAPIImpl
import com.luz.giphy.api.model.Data
import com.luz.giphy.api.model.GiphyResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class GiphyViewModel: ViewModel()
    /*, Observable */
{
    lateinit var gifList: ObservableField<List<Data>>

    private val callbacks : PropertyChangeRegistry by lazy {PropertyChangeRegistry()}

    /*override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        callbacks.remove(callback)
    }*/

    fun getRepository(){
        val giphyService= GiphyAPIImpl().getGiphyService()
        val giphySingle: Single<GiphyResponse> = giphyService.getResponse()

        giphySingle.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: SingleObserver<GiphyResponse> {
                override fun onSubscribe(d: Disposable) {
                    Log.w("ABC", "S")
                }

                override fun onSuccess(response: GiphyResponse) {
                    gifList = ObservableField<List<Data>>(response.gifList)
                }

                override fun onError(e: Throwable) {
                    //handleError(e)
                }
            })

        /*giphySingle.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: Observer<GiphyResponse>{
                override fun onNext(response: GiphyResponse) {
                    handleResults(response)
                }
                override fun onError(e: Throwable) {

                }
                override fun onComplete() {
                }
                override fun onSubscribe(d: Disposable) {
                }
            })*/

        /*giphySingle.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(this::handleResults, this::handleError)*/
    }
}