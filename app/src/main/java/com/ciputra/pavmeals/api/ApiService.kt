package com.ciputra.pavmeals.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("random.php")
    fun getRandomMeals(
        // @Query("month") month: Int,
//        @Query("year") year: Int
    ): Call<MealsLayer1>


}