package com.epicusprogramming.bluweatherapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.epicusprogramming.bluweatherapp.R
import com.epicusprogramming.bluweatherapp.adapters.HourlyAdapter
import com.epicusprogramming.bluweatherapp.ui.WeatherActivity
import com.epicusprogramming.bluweatherapp.ui.WeatherViewModel
import com.epicusprogramming.bluweatherapp.util.Resource
import kotlinx.android.synthetic.main.fragment_hourly.*
import java.util.*


class HourlyFragment : Fragment(R.layout.fragment_hourly) {

    lateinit var hourlyWeatherViewModel: WeatherViewModel
    lateinit var hourlyAdapter: HourlyAdapter
    val TAG = "HourlyFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        ivHourlyToDaily.setOnClickListener {
            it.findNavController().navigate(R.id.action_hourlyFragment_to_dailyFragment)
        }

        hourlyWeatherViewModel = (activity as WeatherActivity).weatherViewModel


        hourlyWeatherViewModel.weatherResponse.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { hourlyResponse ->
                        hourlyAdapter.setData(hourlyResponse.hourly)
                        Log.d(TAG, "now is = ${Calendar.getInstance().timeInMillis / 1000L}")
                        Log.d(TAG, "hourly[0] = ${hourlyResponse.hourly[0]}")
                        Log.d(TAG, "hourly[1] = ${hourlyResponse.hourly[1]}")
//                        if ((hourlyResponse.hourly[0].dt + 3600L) -  )
//                        hourlyWeatherViewModel.saveHourlyList(hourlyResponse.hourly)
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e(TAG, "error occurred: $message")
                    }

                }
                is Resource.Loading -> {
                    Log.d(TAG, "response loading")
                }
            }

        })
        hourlyWeatherViewModel.getSavedHourly().observe(viewLifecycleOwner, {

        })
    }

    fun setupRecyclerView() {
        hourlyAdapter = HourlyAdapter()
        rvHourly.apply {
            adapter = hourlyAdapter
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            val dividerItemDecoration =
                DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL)
            addItemDecoration(dividerItemDecoration)
        }
    }
}