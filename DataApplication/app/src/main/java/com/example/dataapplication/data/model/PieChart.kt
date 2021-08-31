package com.example.dataapplication.data.model

import android.content.ContentValues
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dataapplication.provider.ChartContract

@Entity(tableName = ChartContract.PieChartEntry.TABLE_NAME)
data class PieChart(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var value_1: Double = 0.0,
    var value_2: Double = 0.0,
    var value_3: Double = 0.0,
    var value_4: Double = 0.0,
) {
    companion object {

        fun fromContentValues(values: ContentValues?): PieChart? {
            val pieChart = PieChart()
            return values?.let {

                values.getAsInteger(ChartContract.PieChartEntry.COLUMN_ID)?.let {
                    pieChart.id = it
                }
                pieChart.value_1 =
                    values.getAsDouble(ChartContract.PieChartEntry.COLUMN_VALUE_1) ?: 0.0
                pieChart.value_2 =
                    values.getAsDouble(ChartContract.PieChartEntry.COLUMN_VALUE_2) ?: 0.0
                pieChart.value_3 =
                    values.getAsDouble(ChartContract.PieChartEntry.COLUMN_VALUE_3) ?: 0.0
                pieChart.value_4 =
                    values.getAsDouble(ChartContract.PieChartEntry.COLUMN_VALUE_4) ?: 0.0
                pieChart
            }
        }
    }
}