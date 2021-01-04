package com.epicusprogramming.bluweatherapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.epicusprogramming.bluweatherapp.R
import com.epicusprogramming.bluweatherapp.adapters.DailyAdapter
import com.epicusprogramming.bluweatherapp.ui.WeatherActivity
import com.epicusprogramming.bluweatherapp.ui.WeatherViewModel
import com.epicusprogramming.bluweatherapp.util.Resource
import kotlinx.android.synthetic.main.fragment_daily.*

class DailyFragment : Fragment(R.layout.fragment_daily) {

    lateinit var dailyWeatherViewModel: WeatherViewModel
    lateinit var dailyAdapter: DailyAdapter
    val TAG = "DailyFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        ivDailyToHourly.setOnClickListener {
            it.findNavController().navigate(R.id.action_dailyFragment_to_hourlyFragment)
        }

        dailyWeatherViewModel = (activity as WeatherActivity).weatherViewModel

        dailyWeatherViewModel.weatherResponse.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { dailyResponse ->
                        dailyAdapter.setData(dailyResponse.daily)
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e(TAG, "error occurred: $message")
                    }
                }
                is Resource.Loading -> {
                    Log.d(TAG, "loading started")
                }
            }
        })

    }

    fun setupRecyclerView() {
        dailyAdapter = DailyAdapter()
        rvDaily.apply {
            adapter = dailyAdapter
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            val dividerItemDecoration =
                DividerItemDecoration(this.context, LinearLayoutManager.HORIZONTAL)
            addItemDecoration(dividerItemDecoration)
        }
    }
}