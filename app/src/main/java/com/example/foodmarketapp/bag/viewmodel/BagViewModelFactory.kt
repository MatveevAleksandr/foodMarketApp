package com.example.foodmarketapp.bag.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecases.*

class BagViewModelFactory(
    private val plusItemInBagUseCase: PlusItemInBagUseCase,
    private val minusItemInBagUseCase: MinusItemInBagUseCase,
    private val loadAllBagUseCase: LoadAllBagUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BagViewModel(
            plusItemInBagUseCase = plusItemInBagUseCase,
            minusItemInBagUseCase = minusItemInBagUseCase,
            loadAllBagUseCase = loadAllBagUseCase
        ) as T
    }
}