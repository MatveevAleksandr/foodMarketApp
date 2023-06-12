package com.example.data.category.storage

import com.example.domain.model.CategoryStorageAnswerModel

interface CategoryStorage {
    fun loadCategoryList(): CategoryStorageAnswerModel
}