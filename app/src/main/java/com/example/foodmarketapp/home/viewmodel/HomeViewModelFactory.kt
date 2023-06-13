package com.example.foodmarketapp.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecases.GetCategoryListUseCase

class HomeViewModelFactory(
    private val getCategoryListUseCase: GetCategoryListUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(
            getCategoryListUseCase = getCategoryListUseCase
        ) as T
    }
}