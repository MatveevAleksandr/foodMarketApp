package com.example.domain.model

data class CategoryStorageAnswerModel(
    val isError: Boolean,
    val errorMsg: String,
    val categoryList: List<CategoryModel>?
)