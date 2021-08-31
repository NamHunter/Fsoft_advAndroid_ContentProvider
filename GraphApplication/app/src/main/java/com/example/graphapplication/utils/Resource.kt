package com.example.graphapplication.utils

sealed class Resource<out T> {

    data class Success<out T>(val data: T?) : Resource<T>()

    data class Error(val error: String?) : Resource<Nothing>()
}