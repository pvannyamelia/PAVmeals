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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    var catResponse: ArrayList<CategoryLayer2> = arrayListOf()

//    private var layoutManager: RecyclerView.LayoutManager? = null
//    private var categoryAdapter: RecyclerView.Adapter<CategoryAdapter.ListViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
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

    private fun showChosenCtg(ctg: CategoryLayer2){
        val moveIntent = Intent(requireActivity(), ResultActivity::class.java)
        moveIntent.putExtra(ResultActivity.EXTRA_CTG, ctg.strCategory)
        startActivity(moveIntent)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?){
        super.onViewCreated(itemView, savedInstanceState)
        rv_category.apply{
            rv_category.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            val categoryAdapter = CategoryAdapter(catResponse)
            // On Click Listener
            categoryAdapter.setOnItemClickCallback(object : CategoryAdapter.OnItemClickCallback{
                override fun onItemClicked(data: CategoryLayer2) {
                    showChosenCtg(data)
                }
            })
        }
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                HomeFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}