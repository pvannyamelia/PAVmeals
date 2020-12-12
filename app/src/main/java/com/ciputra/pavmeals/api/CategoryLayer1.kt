package com.ciputra.pavmeals.api

import com.google.gson.annotations.SerializedName

class CategoryLayer1 {
    @SerializedName("categories")
    var categories: List<CategoryLayer2>? = null
}