package com.weatherReports.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.weatherReports.ui.weatherReports.WeatherActivity
import org.json.JSONObject
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

object ErrorHandlingClass {
    fun errorHandlingException(
        context: Context,
        it: Throwable
    ) {
        it.printStackTrace()
        if (it is HttpException) {
            try {
                if (it.code() == 400) {
                    val jObjError =
                        JSONObject(it.response()!!.errorBody()!!.string())
                    Toast.makeText(context, jObjError.getString("message"), Toast.LENGTH_SHORT)
                        .show()
                } else if (it.code() == 401) {
                    Toast.makeText(context, "Please login again", Toast.LENGTH_SHORT).show()
                    context.startActivity(Intent(context, WeatherActivity::class.java))
                    (context as Activity).finishAffinity()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else if (it is ConnectException) {
            Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT)
                .show()
        } else if (it is SocketTimeoutException) {
            Toast.makeText(context, "Please check your internet connection", Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show()
        }
    }
}