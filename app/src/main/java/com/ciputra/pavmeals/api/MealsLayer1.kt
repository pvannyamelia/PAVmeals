package com.ciputra.pavmeals.api

import com.google.gson.annotations.SerializedName

class MealsLayer1 {
    @SerializedName("meals")
    var meals: List<MealsLayer2>? = null
}