package com.example.foodmarketapp.home.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.CategoryModel
import com.example.foodmarketapp.R

class CategoryRecyclerAdapter(val categoryList: List<CategoryModel>): RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val categoryNameTextView = itemView.findViewById<TextView>(R.id.category_name)
        val categoryImageImageView = itemView.findViewById<TextView>(R.id.category_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return CategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.categoryNameTextView.text = categoryList[position].name
//        holder.categoryNameTextView.text = categoryList[position].name
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}