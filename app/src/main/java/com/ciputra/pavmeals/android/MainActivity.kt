package com.ciputra.pavmeals.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ciputra.pavmeals.R
import com.ciputra.pavmeals.api.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.ciputra.pavmeals.api.CategoryLayer1 as CategoryLayer1

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        add some data to model class

//        Api.service<ApiService>()
//               .getCategory()
//               .enqueue(object : Callback<CategoryLayer1> {
//                   override fun onResponse(call: Call<CategoryLayer1>, response: Response<CategoryLayer1>) {
//                        val catResponse = response.body()!!
//                        for (category in catResponse.categories){
//                            allCategory.add(Category(category.strCategory))
//                        }
//                   }
//
//                   override fun onFailure(call: Call<CategoryLayer1>, t: Throwable) {
//                       Log.e("ERROR", t.message.orEmpty())
//                   }
//               })
    }
}