package com.example.foodmarketapp.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecases.CategoryCardClickUseCase
import com.example.domain.usecases.GetCategoryListUseCase

class HomeViewModelFactory(
    private val getCategoryListUseCase: GetCategoryListUseCase,
    private val categoryCardClickUseCase: CategoryCardClickUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(
            getCategoryListUseCase = getCategoryListUseCase,
            categoryCardClickUseCase = categoryCardClickUseCase
        ) as T
    }
}