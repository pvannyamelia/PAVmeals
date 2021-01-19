package com.ciputra.pavmeals.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ciputra.pavmeals.R
import com.ciputra.pavmeals.api.MealsLayer2

class ResultAdapter(private val listResult: ArrayList<MealsLayer2>) : RecyclerView.Adapter<ResultAdapter.ResultViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var iv_meal: ImageView = itemView.findViewById(R.id.iv_meal)
        var tv_meal: TextView = itemView.findViewById(R.id.tv_meal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultAdapter.ResultViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_result, parent, false)
        return ResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val result = listResult?.get(position)

        Glide.with(holder.itemView.context)
                .load(result?.strMealThumb)
//                .apply(RequestOptions().override(120, 120))
                .into(holder.iv_meal)

        holder.tv_meal.text = result.strMeal
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listResult[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int {
        return listResult?.size
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: MealsLayer2)
    }
}