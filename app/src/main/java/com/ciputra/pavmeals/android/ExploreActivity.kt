package com.ciputra.pavmeals.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import com.ciputra.pavmeals.R
import com.ciputra.pavmeals.api.ApiService
import com.ciputra.pavmeals.api.MealsLayer1
import com.ciputra.pavmeals.api.MealsLayer2
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_explore.*
import kotlinx.android.synthetic.main.activity_explore.bottom_nav
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExploreActivity : AppCompatActivity() {
    var mealStr: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore)
        supportActionBar?.hide()
        Api.service<ApiService>()
            .getAreaList()
            .enqueue(object : Callback<MealsLayer1> {
                override fun onResponse(call: Call<MealsLayer1>, response: Response<MealsLayer1>) {
                    var areaResponse: ArrayList<MealsLayer2> = response.body()?.meals!!
                    for(index in 0..areaResponse.size-1){
                        val chip = Chip(cg_area.context)
                        chip.text = areaResponse.get(index).strArea

                        // necessary to get single selection working
                        chip.isClickable = true
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
                    for(index in 0..10){
                        val chip = Chip(cg_ingredient.context)
                        chip.text = ingResponse.get(index).strIngredient
                        chip.isClickable = true
                        cg_ingredient.addView(chip)
                    }
                }

                override fun onFailure(call: Call<MealsLayer1>, t: Throwable) {
                    Log.e("ERROR", t.message.orEmpty())
                }

            })
        bottom_nav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_home -> {
                    val moveHome = Intent(this@ExploreActivity, MainActivity::class.java)
                    startActivity(moveHome)
                }
                R.id.ic_profile -> {
                    val moveProfil = Intent(this@ExploreActivity, ProfileActivity::class.java)
                    startActivity(moveProfil)
                }
            }
            true
        }


    }
}