package com.example.graphapplication.base

import android.util.Log
import com.example.graphapplication.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseRepository {

    suspend fun <T> safeApiCall(apiCall: suspend () -> T?): Resource<T> = withContext(Dispatchers.IO) {
        Log.e("TAG", "safeApiCall: vao day", )
        try {
            Resource.Success(apiCall.invoke())
        } catch (e: Throwable) {
            Resource.Error(e.message)
        }
    }
}