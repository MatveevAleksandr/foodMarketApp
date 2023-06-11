package com.example.domain.usecases

import com.example.domain.model.CategoryModel
import com.example.domain.repository.CategoryRepository

class GetCategoryListUseCase(private val categoryRepository: CategoryRepository) {
    fun execute(): List<CategoryModel>{
        return categoryRepository.loadCategoryList()
    }
}