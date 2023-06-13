package com.example.domain.usecases

import com.example.domain.model.DishStorageAnswerModel
import com.example.domain.repository.DishRepository

class GetDishListByCategoryIDUseCase(private val dishRepository: DishRepository) {
    fun execute(categoryID: Int): DishStorageAnswerModel{
        return dishRepository.loadDishListByCategoryID(categoryID = categoryID)
    }
}