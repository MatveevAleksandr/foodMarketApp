package com.example.foodmarketapp.dish.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.DishModel
import com.example.foodmarketapp.MainActivity
import com.example.foodmarketapp.R
import com.example.foodmarketapp.dish.ui.DishItemDetail
import com.squareup.picasso.Picasso

class DishRecyclerAdapter(
    private val dishList: List<DishModel>, private val addClick: (DishModel) -> Unit
) : RecyclerView.Adapter<DishRecyclerAdapter.DishViewHolder>() {

    class DishViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dishName: TextView = itemView.findViewById(R.id.dish_name)
        val dishImage: ImageView = itemView.findViewById(R.id.dish_image)
        val dishCard: CardView = itemView.findViewById(R.id.dish_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.dish_item, parent, false)
        return DishViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        holder.dishName.text = dishList[position].name
        Picasso.get().load(dishList[position].image_url).fit().into(holder.dishImage)
        holder.dishCard.setOnClickListener {
            val dishDetail = DishItemDetail(dishList[position], addClick)
            dishDetail.show(
                (holder.itemView.context as MainActivity).supportFragmentManager, "custom"
            )
        }
    }

    override fun getItemCount(): Int {
        return dishList.size
    }
}