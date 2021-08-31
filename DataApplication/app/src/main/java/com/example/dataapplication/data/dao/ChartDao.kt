package com.example.dataapplication.data.dao

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.dataapplication.data.model.ColumnChart
import com.example.dataapplication.data.model.PieChart
import com.example.dataapplication.provider.ChartContract

@Dao
interface ChartDao {

    @Insert
    fun insertPieChart(chartItem: PieChart?): Long

    @Query("SELECT * FROM " + ChartContract.PieChartEntry.TABLE_NAME)
    fun getAllPieChartItem(): Cursor

    @Update
    fun updatePieChart(pieChart: PieChart?): Int

    @Insert
    fun insertColumnChart(chartItem: ColumnChart?): Long

    @Query("SELECT * FROM " + ChartContract.ColumnChartEntry.TABLE_NAME)
    fun getAllColumnChartItem(): Cursor

    @Update
    fun updateColumnChart(chartItem: ColumnChart?): Int

    @Query("SELECT * FROM ${ChartContract.ColumnChartEntry.TABLE_NAME} ORDER BY ID DESC LIMIT 1")
    fun getLastColumnChartItem(): Cursor

    @Query("SELECT * FROM ${ChartContract.PieChartEntry.TABLE_NAME} ORDER BY ID DESC LIMIT 1")
    fun getLastPieChartItem(): Cursor
}