package com.eve.testichigo.core.utils

import com.eve.testichigo.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor constructor(private val pref: SharedPrefs) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        //val token = pref.getToken()
        val newRequest = chain.request().newBuilder()
            .build()
        return chain.proceed(newRequest)
    }
}