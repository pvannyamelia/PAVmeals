package com.ciputra.pavmeals.android.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ciputra.pavmeals.R
import com.ciputra.pavmeals.android.Api
import com.ciputra.pavmeals.android.ResultActivity
import com.ciputra.pavmeals.android.SafeClickListener
import com.ciputra.pavmeals.api.ApiService
import com.ciputra.pavmeals.api.MealsLayer1
import com.ciputra.pavmeals.api.MealsLayer2
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_explore.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExploreFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_explore, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Api.service<ApiService>()
            .getAreaList()
            .enqueue(object : Callback<MealsLayer1> {
                override fun onResponse(call: Call<MealsLayer1>, response: Response<MealsLayer1>) {
                    var areaResponse: ArrayList<MealsLayer2> = response.body()?.meals!!
                    for (index in 0..areaResponse.size - 1) {
                        val chip = Chip(cg_area.context)
                        chip.text = areaResponse.get(index).strArea
                        chip.id = index
                        chip.isClickable = true
                        chip.setSafeOnClickListener{
                                val moveIntent = Intent(requireActivity(), ResultActivity::class.java)
                                moveIntent.putExtra(ResultActivity.EXTRA_AREA, chip.text.toString())
                                startActivity(moveIntent)
                        }
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
                    for (index in 0..10) {
                        val chip = Chip(cg_ingredient.context)
                        chip.text = ingResponse.get(index).strIngredient
                        chip.id = index
                        chip.isClickable = true
                        chip.setSafeOnClickListener{
                                val moveIntent = Intent(requireActivity(), ResultActivity::class.java)
                                moveIntent.putExtra(ResultActivity.EXTRA_ING, chip.text.toString())
                                startActivity(moveIntent)
                        }
                        cg_ingredient.addView(chip)
                    }
                }

                override fun onFailure(call: Call<MealsLayer1>, t: Throwable) {
                    Log.e("ERROR", t.message.orEmpty())
                }

            })

        bt_search.setSafeOnClickListener {
                val moveIntent = Intent(requireActivity(), ResultActivity::class.java)
                moveIntent.putExtra(ResultActivity.EXTRA_QUERY, et_search.text.toString())
                startActivity(moveIntent)
        }
    }

    fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
        val safeClickListener = SafeClickListener {
            onSafeClick(it)
        }
        setOnClickListener(safeClickListener)
    }
}