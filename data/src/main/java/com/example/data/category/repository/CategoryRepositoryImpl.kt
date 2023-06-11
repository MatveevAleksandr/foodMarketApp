package com.example.data.category.repository

import com.example.data.category.storage.CategoryStorage
import com.example.domain.model.CategoryModel
import com.example.domain.repository.CategoryRepository

class CategoryRepositoryImpl(private val categoryStorage: CategoryStorage): CategoryRepository {
    override fun loadCategoryList(): List<CategoryModel> {
        return categoryStorage.loadCategoryList()
    }
}