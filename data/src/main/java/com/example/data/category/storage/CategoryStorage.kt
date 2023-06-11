package com.example.data.category.storage

import com.example.domain.model.CategoryModel

interface CategoryStorage {
    fun loadCategoryList(): List<CategoryModel>
}