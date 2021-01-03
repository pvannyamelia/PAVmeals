package com.ciputra.pavmeals.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ciputra.pavmeals.R
import com.ciputra.pavmeals.android.adapter.CategoryAdapter
import com.ciputra.pavmeals.api.ApiService
import com.ciputra.pavmeals.api.CategoryLayer2
import com.ciputra.pavmeals.api.MealsLayer1
import com.ciputra.pavmeals.api.MealsLayer2
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.ciputra.pavmeals.api.CategoryLayer1 as CategoryLayer1

class MainActivity : AppCompatActivity() {
    var index: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        Api.service<ApiService>()
            .getRandomMeals()
            .enqueue(object : Callback<MealsLayer1> {
                override fun onResponse(call: Call<MealsLayer1>, response: Response<MealsLayer1>) {
                    var randResponse: ArrayList<MealsLayer2> = response.body()!!.meals!!
                    tv_titlePick.text = randResponse.get(0).strMeal
                    showglide(randResponse.get(0).strMealThumb)

                }

                override fun onFailure(call: Call<MealsLayer1>, t: Throwable) {
                    Log.e("Error", t.message.orEmpty())
                }
            })

        Api.service<ApiService>()
               .getCategory()
               .enqueue(object : Callback<CategoryLayer1> {
                   override fun onResponse(call: Call<CategoryLayer1>, response: Response<CategoryLayer1>) {
                       var catResponse: ArrayList<CategoryLayer2> = response.body()!!.categories
                       showCategoryRecycler(catResponse)
                   }

                   override fun onFailure(call: Call<CategoryLayer1>, t: Throwable) {
                       Log.e("ERROR", t.message.orEmpty())
                   }
               })

        Api.service<ApiService>()
            .getAreaList()
            .enqueue(object : Callback<MealsLayer1> {
                override fun onResponse(call: Call<MealsLayer1>, response: Response<MealsLayer1>) {
                    var areaResponse: ArrayList<MealsLayer2> = response.body()?.meals!!
                    for(i in 1..10){
                        index = (0..areaResponse.size-1).random()
                        val chip = Chip(cg_area.context)
                        chip.text = areaResponse.get(index).strArea

                            // necessary to get single selection working
                        chip.isClickable = true
                        chip.isCheckable = true
                        cg_area.addView(chip)
                    }
                }

                override fun onFailure(call: Call<MealsLayer1>, t: Throwable) {
                    Log.e("ERROR", t.message.orEmpty())
                }
            })

        Api.service<ApiService>()
            .getIngList()
            .enqueue(object : Callback<MealsLayer1> {
                override fun onResponse(call: Call<MealsLayer1>, response: Response<MealsLayer1>) {
                    var ingResponse: ArrayList<MealsLayer2> = response.body()?.meals!!
                    for(i in 1..10){
                        index = (0..ingResponse.size-1).random()
                        val chip = Chip(cg_ingredient.context)
                        chip.text = ingResponse.get(index).strIngredient
                        chip.isClickable = true
                        chip.isCheckable = true
                        cg_ingredient.addView(chip)
                    }
                }

                override fun onFailure(call: Call<MealsLayer1>, t: Throwable) {
                    Log.e("ERROR", t.message.orEmpty())
                }

            })
    }

    private fun showCategoryRecycler(catResponse: ArrayList<CategoryLayer2>) {
        rv_category.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val categoryAdapter = CategoryAdapter(catResponse)
        rv_category.adapter = categoryAdapter
    }

    private fun showglide(url: String?){
        Glide.with(this)
            .load(url)
            .into(iv_pickThumb)
    }
}