package com.example.foodmarketapp.home.viewmodel

import androidx.lifecycle.ViewModel
import com.example.data.model.CategoryModel
import com.example.domain.usecases.CategoryCardClickUseCase

class HomeViewModel(private val categoryCardClickUseCase: CategoryCardClickUseCase) : ViewModel() {
    fun getCategoryList(): List<CategoryModel> {
        return (1..10).map {
            CategoryModel(id = it, name = "name $it", image_url = "https://$it")
        }.toMutableList()
    }
}