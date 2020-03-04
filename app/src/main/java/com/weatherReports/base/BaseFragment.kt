package com.weatherReports.base

import androidx.fragment.app.Fragment
import com.android.logindemo.webservice.RetrofitUtil
import com.weatherReports.webservices.ApiInterface


open class BaseFragment: Fragment() {

    val apiInterface: ApiInterface by lazy {
        RetrofitUtil.createBaseApiService()
    }

}