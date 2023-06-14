package com.example.foodmarketapp.dish.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecases.GetDishListByCategoryIDUseCase
import com.example.domain.usecases.GetDishListByTagUseCase
import com.example.domain.usecases.PlusItemInBagUseCase

class DishViewModelFactory (
    private val getDishListByCategoryIDUseCase: GetDishListByCategoryIDUseCase,
    private val getDishListByTagUseCase: GetDishListByTagUseCase,
    private val plusItemInBagUseCase: PlusItemInBagUseCase
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DishViewModel(
                getDishListByCategoryIDUseCase = getDishListByCategoryIDUseCase,
                getDishListByTagUseCase = getDishListByTagUseCase,
                plusItemInBagUseCase = plusItemInBagUseCase
            ) as T
        }
    }