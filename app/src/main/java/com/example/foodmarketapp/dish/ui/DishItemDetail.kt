package com.example.foodmarketapp.dish.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.DialogFragment
import com.example.domain.model.DishModel
import com.example.foodmarketapp.R
import com.squareup.picasso.Picasso

class DishItemDetail(private val dishItem: DishModel) : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dish_item_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val dishImage = view.findViewById<ImageView>(R.id.dish_detail_image)
        val dishName = view.findViewById<TextView>(R.id.dish_detail_name)
        val dishPrice = view.findViewById<TextView>(R.id.dish_detail_price)
        val dishWeight = view.findViewById<TextView>(R.id.dish_detail_weight)
        val dishDescription = view.findViewById<TextView>(R.id.dish_detail_description)
        val closeBtn = view.findViewById<CardView>(R.id.closeDishDetail)

        Picasso.get().load(dishItem.image_url).fit().into(dishImage)
        dishName.text = dishItem.name
        dishPrice.text = "${dishItem.price} ₽"
        dishWeight.text = " · ${dishItem.weight}г"
        dishDescription.text = dishItem.description
        closeBtn.setOnClickListener {
            dismiss()
        }

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        super.onViewCreated(view, savedInstanceState)
    }
}