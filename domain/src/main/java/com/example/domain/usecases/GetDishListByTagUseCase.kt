package com.example.domain.usecases

import com.example.domain.model.DishStorageAnswerModel
import com.example.domain.repository.DishRepository

class GetDishListByTagUseCase(private val dishRepository: DishRepository) {
    fun execute(categoryID: Int, tag: String): DishStorageAnswerModel{
        return dishRepository.loadDishListByTag(categoryID = categoryID, tag = tag)
    }
}