package com.example.foodmarketapp.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecases.CategoryCardClickUseCase

class HomeViewModelFactory(
    private val categoryCardClickUseCase: com.example.domain.usecases.CategoryCardClickUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        Log.d("AAA_AAA", "HomeViewModelFactory")
        return HomeViewModel(
            categoryCardClickUseCase
        ) as T
    }
}