package com.example.foodmarketapp.home.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.CategoryModel
import com.example.foodmarketapp.R
import com.squareup.picasso.Picasso

class CategoryRecyclerAdapter(private val categoryList: List<CategoryModel>) :
    RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryNameTextView: TextView = itemView.findViewById(R.id.category_name)
        val categoryImageImageView: ImageView = itemView.findViewById(R.id.category_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return CategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        Picasso.get().load(categoryList[position].image_url).fit().centerCrop()
            .into(holder.categoryImageImageView)
        holder.categoryNameTextView.text = categoryList[position].name
        Log.d("AAA_AAA", categoryList[position].toString())
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}