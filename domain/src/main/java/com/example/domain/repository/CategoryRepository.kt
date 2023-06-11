package com.example.domain.repository

import com.example.domain.model.CategoryModel

interface CategoryRepository {
    fun loadCategoryList(): List<CategoryModel>
}