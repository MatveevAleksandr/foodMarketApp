package com.example.foodmarketapp.home.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.CategoryModel
import com.example.foodmarketapp.R
import com.example.foodmarketapp.dish.ui.CATEGORY_ID_BUNDLE
import com.example.foodmarketapp.dish.ui.CATEGORY_NAME_BUNDLE
import com.squareup.picasso.Picasso

class CategoryRecyclerAdapter(
    private val categoryList: List<CategoryModel>,
    private val navController: NavController
) :
    RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryCard: CardView = itemView.findViewById(R.id.category_card)
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

        holder.categoryCard.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt(CATEGORY_ID_BUNDLE, categoryList[position].id)
            bundle.putString(CATEGORY_NAME_BUNDLE, categoryList[position].name)
            navController.popBackStack(R.id.navigation_home, true)
            navController.navigate(R.id.navigation_dish, bundle)
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}