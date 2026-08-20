package com.example.ai_assistant.API.authAPI

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor(private val basicAuth: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain
            .request().newBuilder()
            .addHeader("Authorization", basicAuth)
            .build()
        Log.d("AuthCredentialsInterceptor", "Credential for getting token have been added REQUEST URL = ${request.url}")
        return chain.proceed(request)
    }
}