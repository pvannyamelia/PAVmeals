package com.ciputra.pavmeals.api

import com.google.gson.annotations.SerializedName

class MealsLayer1 {
    @SerializedName("meals")
    var meals: ArrayList<MealsLayer2>? = null
}