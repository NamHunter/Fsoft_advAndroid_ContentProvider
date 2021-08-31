package com.example.dataapplication.provider

import android.content.*
import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.example.dataapplication.data.dao.ChartDao
import com.example.dataapplication.data.model.ColumnChart
import com.example.dataapplication.data.model.PieChart
import com.example.dataapplication.di.ContentProviderEntryPoint
import dagger.hilt.android.EntryPointAccessors
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

class ChartProvider : ContentProvider() {

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI(
            ChartContract.AUTHORITY,
            ChartContract.PATH_PIE_CHART,
            ChartContract.PieChartEntry.ONE_ITEM
        )
        addURI(
            ChartContract.AUTHORITY,
            ChartContract.PATH_PIE_CHART + "/last",
            ChartContract.PieChartEntry.LAST_ITEMS
        )
        addURI(
            ChartContract.AUTHORITY,
            ChartContract.PATH_COLUMN_CHART,
            ChartContract.ColumnChartEntry.ONE_ITEM
        )
        addURI(
            ChartContract.AUTHORITY,
            ChartContract.PATH_COLUMN_CHART + "/last",
            ChartContract.ColumnChartEntry.LAST_ITEMS
        )
    }

    private val appContext by lazy { context?.applicationContext ?: throw IllegalStateException() }
    private val chartDao by lazy { getDao() }
    private val contentResolver by lazy { appContext.contentResolver }

    override fun onCreate(): Boolean {
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor {
        val cursor: Cursor?

        return when (uriMatcher.match(uri)) {
            ChartContract.PieChartEntry.LAST_ITEMS -> {
                cursor = chartDao.getLastPieChartItem()
                cursor.setNotificationUri(appContext.contentResolver, uri)
                cursor
            }
            ChartContract.ColumnChartEntry.LAST_ITEMS -> {
                cursor = chartDao.getLastColumnChartItem()
                cursor.setNotificationUri(appContext.contentResolver, uri)
                cursor
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun getType(uri: Uri): String =
        when (uriMatcher.match(uri)) {
            ChartContract.PieChartEntry.ONE_ITEM -> ChartContract.PieChartEntry.MIME_TYPE_ITEM
            ChartContract.PieChartEntry.LAST_ITEMS -> ChartContract.PieChartEntry.MIME_TYPE_ITEM
            ChartContract.ColumnChartEntry.ONE_ITEM -> ChartContract.ColumnChartEntry.MIME_TYPE_ITEM
            ChartContract.ColumnChartEntry.LAST_ITEMS -> ChartContract.ColumnChartEntry.MIME_TYPE_ITEM
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }


    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        when (uriMatcher.match(uri)) {
            ChartContract.PieChartEntry.ONE_ITEM -> {
                val id = chartDao.insertPieChart(PieChart.fromContentValues(values))
                Log.e("TAG", "insert: $id")
                if(id < 1){
                    throw IllegalArgumentException("Failed to insert row into: $uri")
                }

                contentResolver.notifyChange(uri, null)
                return ContentUris.withAppendedId(uri, id)
            }
            ChartContract.ColumnChartEntry.ONE_ITEM -> {
                val id = chartDao.insertColumnChart(ColumnChart.fromContentValues(values))
                Log.e("TAG", "insert: $id")
                if(id < 1){
                    throw IllegalArgumentException("Failed to insert row into: $uri")
                }
                contentResolver.notifyChange(uri, null)
                return ContentUris.withAppendedId(uri, id)
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        throw IllegalArgumentException("Cannot support delete : $uri")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        val count: Int
        return when (uriMatcher.match(uri)) {
            ChartContract.PieChartEntry.ONE_ITEM -> {
                count = chartDao.updatePieChart(PieChart.fromContentValues(values))
                contentResolver.notifyChange(uri, null)
                count
            }
            ChartContract.ColumnChartEntry.ONE_ITEM -> {
                count = chartDao.updateColumnChart(ColumnChart.fromContentValues(values))
                contentResolver.notifyChange(uri, null)
                count
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    private fun getDao(): ChartDao =
        EntryPointAccessors.fromApplication(appContext, ContentProviderEntryPoint::class.java)
            .getDao()
}