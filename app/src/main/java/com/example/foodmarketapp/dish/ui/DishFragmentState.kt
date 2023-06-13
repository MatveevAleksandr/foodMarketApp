package com.example.foodmarketapp.dish.ui

import com.example.domain.model.DishModel

sealed class DishFragmentState
data class DishFragmentStateSuccessfulLoad(val dishList: List<DishModel>) : DishFragmentState()
data class DishFragmentStateErrorLoad(val errorMsg: String) : DishFragmentState()