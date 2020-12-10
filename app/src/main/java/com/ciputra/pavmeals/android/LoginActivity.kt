package com.ciputra.pavmeals.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ciputra.pavmeals.R
import com.ciputra.pavmeals.api.ApiService
import com.ciputra.pavmeals.api.MealsLayer1
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Api.service<ApiService>()
            .getRandomMeals()
            .enqueue(object : Callback<MealsLayer1> {
                override fun onResponse(call: Call<MealsLayer1>, response: Response<MealsLayer1>) {
                    TODO("Not yet implemented")
                }

                override fun onFailure(call: Call<MealsLayer1>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }
}