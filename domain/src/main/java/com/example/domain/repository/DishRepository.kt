package com.example.domain.repository

import com.example.domain.model.DishStorageAnswerModel

interface DishRepository {
    fun loadDishListByCategoryID(categoryID: Int): DishStorageAnswerModel
    fun loadDishListByTag(categoryID: Int, tag: String): DishStorageAnswerModel
}