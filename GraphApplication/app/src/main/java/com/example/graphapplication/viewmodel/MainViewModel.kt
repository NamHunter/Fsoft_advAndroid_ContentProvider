package com.example.graphapplication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graphapplication.data.model.ColumnChart
import com.example.graphapplication.data.model.PieChart
import com.example.graphapplication.data.repository.ChartRepository
import com.example.graphapplication.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private var repository: ChartRepository) :
    ViewModel() {

    var mPieChart: MutableLiveData<PieChart> = MutableLiveData()
        private set

    var mColumnChart: MutableLiveData<ColumnChart> = MutableLiveData()
        private set

    fun insertValuesPieChart(pieChart: PieChart) = viewModelScope.launch {
        when (val insertResponse = repository.insertValuesPieChart(pieChart)) {
            is Resource.Success -> {
                Log.e("TAG", "insertValuesPieChart: Thêm thành công")
            }
            is Resource.Error -> {
                Log.e("TAG", "insertValuesPieChart: ${insertResponse.error}")
            }
        }
    }

    fun insertValuesColumnChart(columnChart: ColumnChart) = viewModelScope.launch {
        when (val insertResponse = repository.insertValuesColumnChart(columnChart)) {
            is Resource.Success -> {
                Log.e("TAG", "insertValuesColumnChart: Thêm thành công")
            }
            is Resource.Error -> {
                Log.e("TAG", "insertValuesColumnChart: ${insertResponse.error}")
            }
        }
    }

    fun fetchValuesPieChart() = viewModelScope.launch {
        when (val pieChart = repository.getValuesPieChart()) {
            is Resource.Success -> {
                Log.e("TAG", "fetchValuesPieChart: ${pieChart.data}")
                mPieChart.value = pieChart.data
            }
            is Resource.Error -> {
                Log.e("TAG", "fetchValuesPieChart: error")
            }
        }
    }

    fun fetchValuesColumnChart() = viewModelScope.launch {
        when (val columnChart = repository.getValuesColumnChart()) {
            is Resource.Success -> {
                Log.e("TAG", "fetchValuesColumnChart: ${columnChart.data}")
                mColumnChart.value = columnChart.data
            }
            is Resource.Error -> {
                Log.e("TAG", "fetchValuesColumnChart: error")
            }
        }
    }


    fun updateValuesPieChart() = viewModelScope.launch {
        mPieChart.value?.let {
            mPieChart.value = it
            when (val updateResponse = repository.updateValuesPieChart(it)) {
                is Resource.Success -> {
                    Log.e("TAG", "updateValuesPieChart: Update thanh cong")
                }
                is Resource.Error -> {
                    Log.e("TAG", "updateValuesPieChart: error ${updateResponse.error}")
                }
            }
        }
    }

    fun updateValuesColumnChart() = viewModelScope.launch {
        mColumnChart.value?.let {
            mColumnChart.value = it
            when (val updateResponse = repository.updateValuesColumnChart(it)) {
                is Resource.Success -> {
                    Log.e("TAG", "updateValuesColumnChart: Update thanh cong")
                }
                is Resource.Error -> {
                    Log.e("TAG", "updateValuesColumnChart: error ${updateResponse.error}")
                }
            }
        }
    }
}