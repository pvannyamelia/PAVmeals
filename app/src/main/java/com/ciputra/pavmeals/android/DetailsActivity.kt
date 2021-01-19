package com.ciputra.pavmeals.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.ciputra.pavmeals.R
import com.ciputra.pavmeals.api.ApiService
import com.ciputra.pavmeals.api.MealsLayer1
import com.ciputra.pavmeals.api.MealsLayer2
import kotlinx.android.synthetic.main.activity_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsActivity : AppCompatActivity() {
    companion object{
//        const val EXTRA_TITLE = "extra_title"
//        const val EXTRA_CTG = "extra_ctg"
//        const val EXTRA_AREA = "extra_area"
//        const val EXTRA_INS = "extra_ins"
//        const val EXTRA_THUMB = "extra_thumb"
//        const val EXTRA_TAG = "extra_tag"
//        const val EXTRA_SRC = "extra_src"
//        const val EXTRA_YTB = "extra_ytb"
//        const val EXTRA_ING1 = "extra_ing1"
//        const val EXTRA_ING2 = "extra_ing2"
//        const val EXTRA_ING3 = "extra_ing3"
//        const val EXTRA_MS1 = "extra_ms1"
//        const val EXTRA_MS2 = "extra_ms2"
//        const val EXTRA_MS3 = "extra_ms3"
        const val EXTRA_ID = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        supportActionBar?.hide()

        val strID = intent.getStringExtra(EXTRA_ID)
        Api.service<ApiService>()
            .getMealbyID(strID)
            .enqueue(object : Callback<MealsLayer1> {
                override fun onResponse(call: Call<MealsLayer1>, response: Response<MealsLayer1>) {
                    var pickResponse: ArrayList<MealsLayer2> = response.body()!!.meals!!
                    tv_titlePick.text = pickResponse.get(0).strMeal
                    tv_origin.text = "Origin: "+pickResponse.get(0).strArea
                    tv_ctg.text = "Category: "+pickResponse.get(0).strCategory
                    tv_tag.text = pickResponse.get(0).strTags
                    tv_instruction.text = pickResponse.get(0).strInstructions
                    tv_source.text = "Source: "+pickResponse.get(0).strSource
                    tv_youtube.text = "Youtube: "+pickResponse.get(0).strYoutube
                    tv_ing1.text = "- "+pickResponse.get(0).strMeasure1+" "+pickResponse.get(0).strIngredient1
                    tv_ing2.text = "- "+pickResponse.get(0).strMeasure2+" "+pickResponse.get(0).strIngredient2
                    tv_ing3.text = "- "+pickResponse.get(0).strMeasure3+" "+pickResponse.get(0).strIngredient3
                    showglide(pickResponse.get(0).strMealThumb)
                }

                override fun onFailure(call: Call<MealsLayer1>, t: Throwable) {
                    Log.e("Error", t.message.orEmpty())
                }

            })
    }

    private fun showglide(url: String?){
        Glide.with(this)
            .load(url)
            .into(iv_Thumb)
    }
}