package com.example.dataapplication.data.model

import android.content.ContentValues
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dataapplication.provider.ChartContract

@Entity(tableName = ChartContract.ColumnChartEntry.TABLE_NAME)
data class ColumnChart(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var value_1: Double = 0.0,
    var value_2: Double = 0.0,
    var value_3: Double = 0.0,
    var value_4: Double = 0.0,
    var value_5: Double = 0.0
) {
    companion object {

        fun fromContentValues(values: ContentValues?): ColumnChart? {
            val chartItem = ColumnChart()
            return values?.let {
                values.getAsInteger(ChartContract.ColumnChartEntry.COLUMN_ID)?.let {
                    chartItem.id = it
                }
                chartItem.value_1 =
                    values.getAsDouble(ChartContract.ColumnChartEntry.COLUMN_VALUE_1)?: 0.0
                chartItem.value_2 =
                    values.getAsDouble(ChartContract.ColumnChartEntry.COLUMN_VALUE_2)?: 0.0
                chartItem.value_3 =
                    values.getAsDouble(ChartContract.ColumnChartEntry.COLUMN_VALUE_3)?: 0.0
                chartItem.value_4 =
                    values.getAsDouble(ChartContract.ColumnChartEntry.COLUMN_VALUE_4)?: 0.0
                chartItem.value_5 =
                    values.getAsDouble(ChartContract.ColumnChartEntry.COLUMN_VALUE_5)?: 0.0
                chartItem
            }
        }

    }
}