package com.example.domain.usecases

import com.example.domain.model.CategoryStorageAnswerModel
import com.example.domain.repository.CategoryRepository

class GetCategoryListUseCase(private val categoryRepository: CategoryRepository) {
    fun execute(): CategoryStorageAnswerModel {
        return categoryRepository.loadCategoryList()
    }
}