package com.example.domain.model

data class DishStorageAnswerModel(
    val isError: Boolean, val errorMsg: String, var dishList: MutableList<DishModel>?
)