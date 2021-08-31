package com.example.graphapplication.data.remotesource

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import com.example.graphapplication.data.model.ColumnChart
import com.example.graphapplication.data.model.PieChart
import com.example.graphapplication.utils.ChartContract

class MyChartDataSource(context: Context) {

    private val contentResolver by lazy { context.contentResolver }

    fun getValuesPieChart(): PieChart? {
        val cursor = contentResolver.query(
            Uri.withAppendedPath(
                ChartContract.PieChartEntry.CONTENT_URI,
                ChartContract.PieChartEntry.GET_LAST
            ),
            null,
            null,
            null,
            null
        )
        cursor?.apply {
            if (moveToNext()) {
                val result = PieChart()
                result.id = getInt(getColumnIndex(ChartContract.PieChartEntry.COLUMN_ID))
                result.value_1 =
                    getDouble(getColumnIndex(ChartContract.PieChartEntry.COLUMN_VALUE_1))
                result.value_2 =
                    getDouble(getColumnIndex(ChartContract.PieChartEntry.COLUMN_VALUE_2))
                result.value_3 =
                    getDouble(getColumnIndex(ChartContract.PieChartEntry.COLUMN_VALUE_3))
                result.value_4 =
                    getDouble(getColumnIndex(ChartContract.PieChartEntry.COLUMN_VALUE_4))
                return result
            }
            close()
        }
        return null
    }

    @SuppressLint("Recycle")
    fun getValuesColumnChart(): ColumnChart? {
        val cursor = contentResolver.query(
            Uri.withAppendedPath(
                ChartContract.ColumnChartEntry.CONTENT_URI,
                ChartContract.ColumnChartEntry.GET_LAST
            ),
            null,
            null,
            null,
            null
        )
        cursor?.apply {
            if (moveToNext()) {
                val result = ColumnChart()
                result.id = getInt(getColumnIndex(ChartContract.ColumnChartEntry.COLUMN_ID))
                result.value_1 =
                    getDouble(getColumnIndex(ChartContract.ColumnChartEntry.COLUMN_VALUE_1))
                result.value_2 =
                    getDouble(getColumnIndex(ChartContract.ColumnChartEntry.COLUMN_VALUE_2))
                result.value_3 =
                    getDouble(getColumnIndex(ChartContract.ColumnChartEntry.COLUMN_VALUE_3))
                result.value_4 =
                    getDouble(getColumnIndex(ChartContract.ColumnChartEntry.COLUMN_VALUE_4))
                return result
            }
            close()
        }
        return null
    }

    fun insertValuesPieChart(pieChart: PieChart): Uri? {
        val values = ContentValues().apply {
            put(ChartContract.PieChartEntry.COLUMN_VALUE_1, pieChart.value_1)
            put(ChartContract.PieChartEntry.COLUMN_VALUE_2, pieChart.value_2)
            put(ChartContract.PieChartEntry.COLUMN_VALUE_3, pieChart.value_3)
            put(ChartContract.PieChartEntry.COLUMN_VALUE_4, pieChart.value_4)
        }
        return contentResolver.insert(ChartContract.PieChartEntry.CONTENT_URI, values)
    }

    fun insertValuesColumnChart(columnChart: ColumnChart): Uri? {
        val values = ContentValues().apply {
            put(ChartContract.ColumnChartEntry.COLUMN_VALUE_1, columnChart.value_1)
            put(ChartContract.ColumnChartEntry.COLUMN_VALUE_2, columnChart.value_2)
            put(ChartContract.ColumnChartEntry.COLUMN_VALUE_3, columnChart.value_3)
            put(ChartContract.ColumnChartEntry.COLUMN_VALUE_4, columnChart.value_4)
            put(ChartContract.ColumnChartEntry.COLUMN_VALUE_5, columnChart.value_5)
        }
        return contentResolver.insert(ChartContract.ColumnChartEntry.CONTENT_URI, values)
    }

    fun updateValuesPieChart(pieChart: PieChart): Int {
        val values = ContentValues().apply {
            put(ChartContract.PieChartEntry.COLUMN_ID, pieChart.id)
            put(ChartContract.PieChartEntry.COLUMN_VALUE_1, pieChart.value_1)
            put(ChartContract.PieChartEntry.COLUMN_VALUE_2, pieChart.value_2)
            put(ChartContract.PieChartEntry.COLUMN_VALUE_3, pieChart.value_3)
            put(ChartContract.PieChartEntry.COLUMN_VALUE_4, pieChart.value_4)
        }
        return contentResolver.update(ChartContract.PieChartEntry.CONTENT_URI, values, null, null)
    }

    fun updateValuesColumnChart(columnChart: ColumnChart): Int {
        val values = ContentValues().apply {
            put(ChartContract.ColumnChartEntry.COLUMN_ID, columnChart.id)
            put(ChartContract.ColumnChartEntry.COLUMN_VALUE_1, columnChart.value_1)
            put(ChartContract.ColumnChartEntry.COLUMN_VALUE_2, columnChart.value_2)
            put(ChartContract.ColumnChartEntry.COLUMN_VALUE_3, columnChart.value_3)
            put(ChartContract.ColumnChartEntry.COLUMN_VALUE_4, columnChart.value_4)
            put(ChartContract.ColumnChartEntry.COLUMN_VALUE_5, columnChart.value_5)
        }
        return contentResolver.update(
            ChartContract.ColumnChartEntry.CONTENT_URI,
            values,
            null,
            null
        )
    }
}