package com.capstonebangkit.dishcover.okhttpclient

import android.content.Context
import com.capstonebangkit.dishcover.interception.AuthInterceptor
import okhttp3.OkHttpClient

class OkHttpClient(val context : Context) {
    fun okhttpClient() : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(context))
            .build()
    }

}