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
    fun getAreaList(
        @Query("a") area: String = "list"
    ): Call<MealsLayer1>

    @GET("list.php")
    fun getCatList(
        @Query("c") category: String = "list"
    ): Call<MealsLayer1>

    @GET("list.php")
    fun getIngList(
        @Query("i") ingredients: String = "list"
    ): Call<MealsLayer1>

    @GET("categories.php")
    fun getCategory(): Call<CategoryLayer1>
}