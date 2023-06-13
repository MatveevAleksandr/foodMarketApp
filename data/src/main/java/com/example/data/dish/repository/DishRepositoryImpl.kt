package com.example.data.dish.repository

import com.example.data.dish.storage.DishStorage
import com.example.domain.model.DishModel
import com.example.domain.model.DishStorageAnswerModel
import com.example.domain.repository.DishRepository

class DishRepositoryImpl(private val dishStorage: DishStorage) : DishRepository {
    override fun loadDishListByCategoryID(categoryID: Int): DishStorageAnswerModel {
//        т.к. в апи логика отбора блюд по номеру категории не заложена, а по идее должна присутствовать, то логику отбора можно сделалть здесь
        return dishStorage.loadDishList()
    }

    override fun loadDishListByTag(categoryID: Int, tag: String): DishStorageAnswerModel {
        val storageResult = dishStorage.loadDishList()
        if (!storageResult.isError && !storageResult.dishList.isNullOrEmpty()) {
            val filteredDishList = mutableListOf<DishModel>()
            storageResult.dishList!!.forEach {
                if (it.tags.contains(tag)) {
                    filteredDishList.add(it)
                }
            }
            storageResult.dishList = filteredDishList
        }
        return storageResult
    }
}