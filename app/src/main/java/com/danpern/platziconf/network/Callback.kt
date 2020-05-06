package com.danpern.platziconf.network

import java.lang.Exception

interface Callback<T> {
    fun onSucced(result: T)
    fun onFailed(exception: Exception)
}