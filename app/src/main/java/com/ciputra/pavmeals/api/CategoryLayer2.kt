package com.ciputra.pavmeals.api

import com.google.gson.annotations.SerializedName

class CategoryLayer2 {
    @SerializedName("idCategory")
    var idCategory: String? = null

    @SerializedName("strCategory")
    var strCategory: String? = null

    @SerializedName("strCategoryThumb")
    var strCategoryThumb: String? = null

    @SerializedName("strCategoryDescription")
    var strCategoryDescription: String? = null
}