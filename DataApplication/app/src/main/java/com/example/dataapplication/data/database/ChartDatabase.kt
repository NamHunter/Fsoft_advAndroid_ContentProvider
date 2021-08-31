package com.example.dataapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dataapplication.data.dao.ChartDao
import com.example.dataapplication.data.model.ColumnChart
import com.example.dataapplication.data.model.PieChart

@Database(entities = [PieChart::class, ColumnChart::class], exportSchema = true, version = 1)
abstract class ChartDatabase : RoomDatabase() {
    abstract fun getChartItemDao(): ChartDao

    companion object{
        const val DATABASE_NAME = "chart.db"
    }
}