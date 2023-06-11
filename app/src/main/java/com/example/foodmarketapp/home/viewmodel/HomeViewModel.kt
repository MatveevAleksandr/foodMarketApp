package com.example.foodmarketapp.home.viewmodel

import androidx.lifecycle.ViewModel
import com.example.domain.model.CategoryModel
import com.example.domain.usecases.CategoryCardClickUseCase
import com.example.domain.usecases.GetCategoryListUseCase

class HomeViewModel(
    private val getCategoryListUseCase: GetCategoryListUseCase,
    private val categoryCardClickUseCase: CategoryCardClickUseCase
) : ViewModel() {
    fun getCategoryList(): List<CategoryModel> {
        return getCategoryListUseCase.execute()
    }
}