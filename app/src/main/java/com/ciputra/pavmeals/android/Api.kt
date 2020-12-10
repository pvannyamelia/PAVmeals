package com.ciputra.pavmeals.android

object Api {
    @JvmStatic
    inline fun <reified T> service() : T {
        return RetrofitClient.retrofitBuilder().create(T::class.java)
    }
}