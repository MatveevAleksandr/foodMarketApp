package com.example.foodmarketapp.home.ui

import com.example.domain.model.CategoryModel

sealed class HomeFragmentState
data class HomeFragmentStateSuccessfulLoad(val categoryList: List<CategoryModel>): HomeFragmentState()
data class HomeFragmentStateErrorLoad(val errorMsg: String): HomeFragmentState()