package com.example.data.dish.storage

import com.example.domain.model.DishStorageAnswerModel

interface DishStorage {
    fun loadDishList(): DishStorageAnswerModel
}