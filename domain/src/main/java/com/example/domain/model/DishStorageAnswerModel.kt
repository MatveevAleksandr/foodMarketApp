package com.example.domain.model

data class DishStorageAnswerModel(
    val isError: Boolean, val errorMsg: String, val dishList: List<DishModel>?
)