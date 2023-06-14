package com.example.foodmarketapp.bag.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.BagItemModel
import com.example.domain.model.DishModel
import com.example.foodmarketapp.R
import com.squareup.picasso.Picasso

class BagRecyclerAdapter(
    private val bagItemList: List<BagItemModel>,
    private val plusClick: (DishModel) -> Unit,
    private val minusClick: (Int) -> Unit
) : RecyclerView.Adapter<BagRecyclerAdapter.BagViewHolder>() {

    class BagViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dishName: TextView = itemView.findViewById(R.id.dish_bag_name)
        val dishImage: ImageView = itemView.findViewById(R.id.dish_bag_image)
        val dishPrice: TextView = itemView.findViewById(R.id.dish_bag_price)
        val dishWeight: TextView = itemView.findViewById(R.id.dish_bag_weight)
        val dishCount: TextView = itemView.findViewById(R.id.dish_bag_count)
        val minusCard: CardView = itemView.findViewById(R.id.dish_bag_minus)
        val plusCard: CardView = itemView.findViewById(R.id.dish_bag_plus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BagViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.bag_item, parent, false)
        return BagViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BagViewHolder, position: Int) {
        holder.dishName.text = bagItemList[position].dish.name
        Picasso.get().load(bagItemList[position].dish.image_url).fit().into(holder.dishImage)
        holder.dishPrice.text = "${bagItemList[position].dish.price} ₽"
        holder.dishWeight.text = " · ${bagItemList[position].dish.weight}г"
        holder.dishCount.text = bagItemList[position].count.toString()
        holder.minusCard.setOnClickListener {
            minusClick(bagItemList[position].dish.id)
        }
        holder.plusCard.setOnClickListener {
            plusClick(bagItemList[position].dish)
        }
    }

    override fun getItemCount(): Int {
        return bagItemList.size
    }
}