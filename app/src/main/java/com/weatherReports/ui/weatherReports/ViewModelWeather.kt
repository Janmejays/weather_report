package com.weatherReports.ui.weatherReports


import androidx.lifecycle.MutableLiveData

import com.weatherReports.base.BaseViewModel

import com.weatherReports.model.ResponseWeatherCurrent
import com.weatherReports.model.ResponseWeatherForecast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ViewModelWeather() : BaseViewModel() {

    private var dis: Disposable? = null
    var throwable = MutableLiveData<Throwable>()
    var mResponseWeather = MutableLiveData<ResponseWeatherCurrent>()
    var mResponseWeatherForecast = MutableLiveData<ResponseWeatherForecast>()

    fun getWeatherCurrent(city: String) {

        dis = apiInterface.weatherCurrentApi(q = city)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { onSuccess(it) },
                { onError(it) }
            )
    }
    private fun onSuccess(it: ResponseWeatherCurrent) {
        mResponseWeather.value = it
    }



    fun getWeatherForecast(city: String) {

        dis = apiInterface.weatherForecastApi(q = city)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { onSuccessForecast(it) },
                { onError(it) }
            )
    }

    private fun onSuccessForecast(it: ResponseWeatherForecast?) {
        mResponseWeatherForecast.value=it
    }


    private fun onError(it: Throwable) {
        throwable.value = it
    }
}