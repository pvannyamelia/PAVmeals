package com.ciputra.pavmeals.android.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ciputra.pavmeals.R
import com.ciputra.pavmeals.android.Api
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.bumptech.glide.Glide
import com.ciputra.pavmeals.android.ResultActivity
import com.ciputra.pavmeals.android.adapter.CategoryAdapter
import com.ciputra.pavmeals.api.*
import kotlinx.android.synthetic.main.fragment_home.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    var catResponse: ArrayList<CategoryLayer2> = arrayListOf()
    lateinit var viewhome: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }
    }

    private fun showglide(url: String?){
        Glide.with(this)
                .load(url)
                .into(iv_pickThumb)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewhome = inflater.inflate(R.layout.fragment_home, container, false)

        // Inflate the layout for this fragment
        return viewhome
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?){
        super.onViewCreated(itemView, savedInstanceState)
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
                    tv_ing1.text = "- "+randResponse.get(0).strMeasure1+" "+randResponse.get(0).strIngredient1
                    tv_ing2.text = "- "+randResponse.get(0).strMeasure2+" "+randResponse.get(0).strIngredient2
                    tv_ing3.text = "- "+randResponse.get(0).strMeasure3+" "+randResponse.get(0).strIngredient3
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

        viewhome.rv_category.layoutManager = LinearLayoutManager(
            this.requireActivity(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
    }

    private fun showCategoryRecycler(){
        val categoryAdapter = CategoryAdapter(catResponse)
        rv_category.adapter = categoryAdapter
        // On Click Listener
        categoryAdapter.setOnItemClickCallback(object : CategoryAdapter.OnItemClickCallback{
            override fun onItemClicked(data: CategoryLayer2) {
                val moveIntent = Intent(requireActivity(), ResultActivity::class.java)
                moveIntent.putExtra(ResultActivity.EXTRA_CTG, data.strCategory)
                startActivity(moveIntent)
            }
        })
    }
}