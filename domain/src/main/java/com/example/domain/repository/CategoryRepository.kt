package com.example.domain.repository

import com.example.domain.model.CategoryStorageAnswerModel

interface CategoryRepository {
    fun loadCategoryList(): CategoryStorageAnswerModel
}