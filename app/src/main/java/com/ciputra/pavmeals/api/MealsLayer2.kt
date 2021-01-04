package com.ciputra.pavmeals.api

import com.google.gson.annotations.SerializedName

class MealsLayer2 {
    // Postman Group1 API
    @SerializedName("idMeal")
    var idMeal: String? = null

    @SerializedName("strMeal")
    var strMeal: String? = null

    @SerializedName("strDrinkAlternate")
    var strDrinkAlternate: String? = null

    @SerializedName("strCategory")
    var strCategory: String? = null

    @SerializedName("strArea")
    var strArea: String? = null

    @SerializedName("strInstructions")
    var strInstructions: String? = null

    @SerializedName("strMealThumb")
    var strMealThumb: String? = null

    @SerializedName("strTags")
    var strTags: String? = null

    @SerializedName("strYoutube")
    var strYoutube: String? = null

    @SerializedName("strSource")
    var strSource: String? = null

    @SerializedName("strIngredient1")
    var strIngredient1: String? = null

    @SerializedName("strIngredient2")
    var strIngredient2: String? = null

    @SerializedName("strIngredient3")
    var strIngredient3: String? = null

    @SerializedName("strMeasure1")
    var strMeasure1: String? = null

    @SerializedName("strMeasure2")
    var strMeasure2: String? = null

    @SerializedName("strMeasure3")
    var strMeasure3: String? = null

    // List all Ingredient API
    @SerializedName("idIngredient")
    var idIngredient: String? = null

    @SerializedName("strIngredient")
    var strIngredient: String? = null

    @SerializedName("strDescription")
    var strDescription: String? = null

    @SerializedName("strType")
    var strType: String? = null
}