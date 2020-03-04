package com.weatherReports.base

import androidx.lifecycle.ViewModel
import com.android.logindemo.webservice.RetrofitUtil

import com.weatherReports.webservices.ApiInterface

abstract class BaseViewModel: ViewModel() {


    val apiInterface: ApiInterface by lazy {
        RetrofitUtil.createBaseApiService()
    }

}