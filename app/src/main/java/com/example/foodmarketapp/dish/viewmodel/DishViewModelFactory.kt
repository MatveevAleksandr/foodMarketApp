package com.example.foodmarketapp.dish.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecases.GetDishListByCategoryIDUseCase
import com.example.domain.usecases.GetDishListByTagUseCase

class DishViewModelFactory (
    private val getDishListByCategoryIDUseCase: GetDishListByCategoryIDUseCase,
    private val getDishListByTagUseCase: GetDishListByTagUseCase
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DishViewModel(
                getDishListByCategoryIDUseCase = getDishListByCategoryIDUseCase,
                getDishListByTagUseCase = getDishListByTagUseCase
            ) as T
        }
    }