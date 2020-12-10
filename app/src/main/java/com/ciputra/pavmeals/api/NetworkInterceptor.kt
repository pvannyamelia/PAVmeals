package com.ciputra.pavmeals.api

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val interceptedRequest: Request = chain.request()

        val request: Request = interceptedRequest.newBuilder()
            .method(interceptedRequest.method, interceptedRequest.body)
            .build()

        return chain.proceed(request)
    }
}