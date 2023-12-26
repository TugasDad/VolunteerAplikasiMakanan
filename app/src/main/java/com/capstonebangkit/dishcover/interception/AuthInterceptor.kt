package com.capstonebangkit.dishcover.interception

import android.content.Context
import com.capstonebangkit.dishcover.sharepref.TokenSharePref
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(val context : Context) : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request()

        // get token and add a header
        val tokenSharePref = TokenSharePref(context).getToken()
        requestBuilder.newBuilder()
            .addHeader("Authorization", "Bearer $tokenSharePref")
            .build()

        return chain.proceed(requestBuilder)
    }
}