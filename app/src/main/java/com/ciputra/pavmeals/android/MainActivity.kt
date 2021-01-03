package com.ciputra.pavmeals.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ciputra.pavmeals.R
import com.ciputra.pavmeals.android.adapter.CategoryAdapter
import com.ciputra.pavmeals.api.ApiService
import com.ciputra.pavmeals.api.CategoryLayer2
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.ciputra.pavmeals.api.CategoryLayer1 as CategoryLayer1

class MainActivity : AppCompatActivity() {
    var catResponse: ArrayList<CategoryLayer2> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Api.service<ApiService>()
               .getCategory()
               .enqueue(object : Callback<CategoryLayer1> {
                   override fun onResponse(call: Call<CategoryLayer1>, response: Response<CategoryLayer1>) {
                       catResponse = response.body()!!.categories
                       showCategoryRecycler(catResponse)
                   }

                   override fun onFailure(call: Call<CategoryLayer1>, t: Throwable) {
                       Log.e("ERROR", t.message.orEmpty())
                   }
               })

    }

    private fun showCategoryRecycler(catResponse: ArrayList<CategoryLayer2>) {
        rv_category.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val categoryAdapter = CategoryAdapter(catResponse)
        rv_category.adapter = categoryAdapter
    }
}