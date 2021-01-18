package com.ciputra.pavmeals.android.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ciputra.pavmeals.R
import com.ciputra.pavmeals.android.Api
import com.ciputra.pavmeals.api.ApiService
import com.ciputra.pavmeals.api.MealsLayer1
import com.ciputra.pavmeals.api.MealsLayer2
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_explore.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ExploreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExploreFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

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

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ExploreFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ExploreFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}