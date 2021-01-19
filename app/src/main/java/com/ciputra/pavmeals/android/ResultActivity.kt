package com.ciputra.pavmeals.android

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ciputra.pavmeals.R
import com.ciputra.pavmeals.android.adapter.ResultAdapter
import com.ciputra.pavmeals.api.ApiService
import com.ciputra.pavmeals.api.MealsLayer1
import com.ciputra.pavmeals.api.MealsLayer2
import kotlinx.android.synthetic.main.activity_result.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ResultActivity : AppCompatActivity() {
    var itemResponse: ArrayList<MealsLayer2> = arrayListOf()

    companion object{
        const val EXTRA_CTG = "extra_ctg"
        const val EXTRA_AREA = "extra_area"
        const val EXTRA_ING = "extra_ing"
        const val EXTRA_QUERY = "extra_query"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        supportActionBar?.hide()

        //get data from intent
        val strCtg = intent.getStringExtra(EXTRA_CTG)
        val strArea = intent.getStringExtra(EXTRA_AREA)
        val strIng = intent.getStringExtra(EXTRA_ING)
        val strQuery = intent.getStringExtra(EXTRA_QUERY)

        if(strCtg != null) {
            Api.service<ApiService>()
                .filterByCategory(category = strCtg)
                .enqueue(object : Callback<MealsLayer1> {
                    override fun onResponse(
                        call: Call<MealsLayer1>,
                        response: Response<MealsLayer1>
                    ) {
                        showResult(response, strCtg)
                    }

                    override fun onFailure(call: Call<MealsLayer1>, t: Throwable) {
                        Log.e("ERROR", t.message.orEmpty())
                    }
                })
        }else if (strQuery != null){
            Api.service<ApiService>()
                .getSearch(strQuery)
                .enqueue(object : Callback<MealsLayer1>{
                    override fun onResponse(
                        call: Call<MealsLayer1>,
                        response: Response<MealsLayer1>
                    ) {
                        showResult(response, strQuery)
                    }

                    override fun onFailure(call: Call<MealsLayer1>, t: Throwable) {
                        Log.e("ERROR", t.message.orEmpty())
                    }
                })
        }
    }

    private fun showResult(response: Response<MealsLayer1>, str: String) {
        tv_titleResult.text = "Search result: " + str
        itemResponse = response.body()!!.meals!!
        showResponseRecycler()
    }

    private fun showResponseRecycler() {
        rv_result.layoutManager = LinearLayoutManager(this)
        val resultAdapter = ResultAdapter(itemResponse)
        rv_result.adapter = resultAdapter
    }
}