package com.example.graphapplication.data.repository

import com.example.graphapplication.base.BaseRepository
import com.example.graphapplication.data.model.ColumnChart
import com.example.graphapplication.data.model.PieChart
import com.example.graphapplication.data.remotesource.MyChartDataSource
import javax.inject.Inject

class ChartRepository @Inject constructor(private var myChartDataSource: MyChartDataSource) :
    BaseRepository() {

    suspend fun getValuesPieChart() = safeApiCall {
        myChartDataSource.getValuesPieChart()
    }

    suspend fun getValuesColumnChart()= safeApiCall {
        myChartDataSource.getValuesColumnChart()
    }

    suspend fun insertValuesPieChart(pieChart: PieChart)= safeApiCall {
        myChartDataSource.insertValuesPieChart(pieChart)
    }

    suspend fun insertValuesColumnChart(columnChart: ColumnChart)= safeApiCall {
        myChartDataSource.insertValuesColumnChart(columnChart)
    }

    suspend fun updateValuesPieChart(pieChart: PieChart)= safeApiCall {
        myChartDataSource.updateValuesPieChart(pieChart)
    }

    suspend fun updateValuesColumnChart(columnChart: ColumnChart)= safeApiCall {
        myChartDataSource.updateValuesColumnChart(columnChart)
    }
}