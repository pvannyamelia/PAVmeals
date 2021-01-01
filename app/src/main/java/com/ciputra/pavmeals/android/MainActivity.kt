package com.ciputra.pavmeals.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ciputra.pavmeals.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_area.setHasFixedSize(true)
        rv_category.setHasFixedSize(true)
        rv_ingredient.setHasFixedSize(true)
    }

    private fun showCategoryRecycler(){

    }
}