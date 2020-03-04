package com.weatherReports.base

import android.os.Build
import android.os.Bundle

import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.android.logindemo.webservice.RetrofitUtil
import com.weatherReports.webservices.ApiInterface


open class BaseActivity : AppCompatActivity() {



    val apiInterface: ApiInterface by lazy {
        RetrofitUtil.createBaseApiService()
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onStop() {
        super.onStop()

    }


    fun switchFragment(frag1: Fragment, manager: FragmentManager, bundle: Bundle?) {
        var count = 0
        if (bundle != null) {
            frag1.arguments = bundle
        }
        if (manager.backStackEntryCount > 0) {
        }
    }
}