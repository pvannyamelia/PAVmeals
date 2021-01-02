package com.ciputra.pavmeals.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("random.php")
    fun getRandomMeals(
        // @Query("month") month: Int,
//        @Query("year") year: Int
    ): Call<MealsLayer1>

    @GET("list.php")
    fun getList(
        @Query("c") category: String = "list",
        @Query("a") area: String = "list",
        @Query("i") ingredients: String = "list"
    ): Call<MealsLayer1>

    @GET("categories.php")
    fun getCategory(): Call<CategoryLayer1>
}