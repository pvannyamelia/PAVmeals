package com.ciputra.pavmeals.android.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ciputra.pavmeals.R
import com.ciputra.pavmeals.api.CategoryLayer1
import com.ciputra.pavmeals.api.CategoryLayer2

class CategoryAdapter(private val listCategory: ArrayList<CategoryLayer2>) : RecyclerView.Adapter<CategoryAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_cat: TextView = itemView.findViewById(R.id.tv_cat)
        var iv_cat: ImageView = itemView.findViewById(R.id.iv_cat)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapter.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_category, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ListViewHolder, position: Int) {
        val category = listCategory?.get(position)

        Glide.with(holder.itemView.context)
            .load(category?.strCategoryThumb)
            .apply(RequestOptions().override(120, 120))
            .into(holder.iv_cat)

        holder.tv_cat.text = category.strCategory
        holder.itemView.setOnClickListener{onItemClickCallback.onItemClicked(listCategory[holder.adapterPosition])}
    }

    override fun getItemCount(): Int {
        return listCategory?.size
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: CategoryLayer2)
    }
}