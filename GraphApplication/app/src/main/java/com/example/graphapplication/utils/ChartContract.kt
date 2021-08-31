package com.example.graphapplication.utils

import android.net.Uri

object ChartContract {
    private const val CONTENT_SCHEME = "content://"
    private const val AUTHORITY = "com.example.dataapplication.provider"
    val BASE_CONTENT_URI: Uri = Uri.parse("$CONTENT_SCHEME$AUTHORITY")
    private const val PATH_PIE_CHART = "PieChart"
    private const val PATH_COLUMN_CHART = "ColumnChart"

    object PieChartEntry {
        const val COLUMN_ID = "id"
        const val COLUMN_VALUE_1 = "value_1"
        const val COLUMN_VALUE_2 = "value_2"
        const val COLUMN_VALUE_3 = "value_3"
        const val COLUMN_VALUE_4 = "value_4"
        val CONTENT_URI: Uri = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PIE_CHART)

        const val GET_LAST = "last"
    }

    object ColumnChartEntry {
        const val COLUMN_ID = "id"
        const val COLUMN_VALUE_1 = "value_1"
        const val COLUMN_VALUE_2 = "value_2"
        const val COLUMN_VALUE_3 = "value_3"
        const val COLUMN_VALUE_4 = "value_4"
        const val COLUMN_VALUE_5 = "value_5"
        val CONTENT_URI: Uri = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_COLUMN_CHART)

        const val GET_LAST = "last"
    }
}