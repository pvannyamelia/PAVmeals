package com.ciputra.pavmeals.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
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

class MainActivity : AppCompatActivity(){
    var catResponse: ArrayList<CategoryLayer2> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        bottom_nav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_profile -> {
                    val moveProfil = Intent(this@MainActivity, ProfileActivity::class.java)
                    startActivity(moveProfil)
                }
                R.id.ic_explore -> {
                    val moveExplore = Intent(this@MainActivity, ExploreActivity::class.java)
                    startActivity(moveExplore)
                }
            }
            true
        }

        Api.service<ApiService>()
            .getRandomMeals()
            .enqueue(object : Callback<MealsLayer1> {
                override fun onResponse(call: Call<MealsLayer1>, response: Response<MealsLayer1>) {
                    var randResponse: ArrayList<MealsLayer2> = response.body()!!.meals!!
                    tv_mealsotd.text = "Meal of The Day: "+randResponse.get(0).strMeal
                    tv_titlePick.text = "Origin: "+randResponse.get(0).strArea
                    tv_tag.text = randResponse.get(0).strCategory
                    tv_instruction.text = randResponse.get(0).strInstructions
                    tv_source.text = "Source: "+randResponse.get(0).strSource
                    tv_youtube.text = "Youtube: "+randResponse.get(0).strYoutube
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
                       catResponse = response.body()!!.categories
                       showCategoryRecycler()
                   }

                   override fun onFailure(call: Call<CategoryLayer1>, t: Throwable) {
                       Log.e("ERROR", t.message.orEmpty())
                   }
               })
    }

    private fun showglide(url: String?){
        Glide.with(this)
            .load(url)
            .into(iv_pickThumb)
    }

    private fun showCategoryRecycler() {
        rv_category.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val categoryAdapter = CategoryAdapter(catResponse)
        rv_category.adapter = categoryAdapter

        // On Click Listener
        categoryAdapter.setOnItemClickCallback(object : CategoryAdapter.OnItemClickCallback{
            override fun onItemClicked(data: CategoryLayer2) {
                showChosenCtg(data)
            }
        })
    }

    private fun showChosenCtg(ctg: CategoryLayer2){
        val moveIntent = Intent(this@MainActivity, ResultActivity::class.java)
        moveIntent.putExtra(ResultActivity.EXTRA_CTG, ctg.strCategory)
        startActivity(moveIntent)
    }
}