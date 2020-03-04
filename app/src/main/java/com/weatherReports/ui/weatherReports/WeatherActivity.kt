package com.weatherReports.ui.weatherReports

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.weatherReports.R
import com.weatherReports.base.BaseActivity
import com.weatherReports.utils.ErrorHandlingClass


@Suppress("DEPRECATION")
class WeatherActivity : BaseActivity() {
    private lateinit var viewModel: ViewModelWeather

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inIt()
        myObservers()
        getWeathers()
    }


    private fun inIt() {

        viewModel = ViewModelProviders.of(this).get(ViewModelWeather::class.java)

    }

    private fun myObservers() {
        viewModel.mResponseWeather.observe(this, Observer {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
            viewModel.getWeatherForecast("Dehli")

        })

        viewModel.mResponseWeatherForecast.observe(this, Observer {
            Toast.makeText(this, it.cod, Toast.LENGTH_SHORT).show()
            openbottomSheet()
        })
        viewModel.throwable.observe(this, Observer {
            ErrorHandlingClass.errorHandlingException(this, it)
        })

    }

    private fun openbottomSheet() {
        val view = layoutInflater.inflate(R.layout.bottom_sheet_layout, null)
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(view)
        dialog.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog.show()
        dialog.setCanceledOnTouchOutside(false)

        val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(view)
        behavior.setBottomSheetCallback(object : BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }

            }

        })

    }

    private fun getWeathers() {
        viewModel.getWeatherCurrent("Noida")
    }


}
