package com.example.foodmarketapp.dish.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmarketapp.R

class DishTypeRecyclerAdapter(
    private val dishTypeList: List<String>, private val dishTypeCLick: (String) -> Unit
) : RecyclerView.Adapter<DishTypeRecyclerAdapter.DishTypeViewHolder>() {

    class DishTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dishTypeName: TextView = itemView.findViewById(R.id.dish_type_name)
        val dishTypeCard: CardView = itemView.findViewById(R.id.dish_type_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishTypeViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.dish_type_item, parent, false)
        return DishTypeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DishTypeViewHolder, position: Int) {
        holder.dishTypeName.text = dishTypeList[position]
        holder.dishTypeCard.setOnClickListener {
            dishTypeCLick(dishTypeList[position])
        }
    }

    override fun getItemCount(): Int {
        return dishTypeList.size
    }
}