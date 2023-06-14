package com.example.domain.usecases

import com.example.domain.model.BagItemModel
import com.example.domain.model.DishModel
import com.example.domain.repository.BagRepository


class PlusItemInBagUseCase(private val repo: BagRepository) {
    fun execute(item: DishModel) {
        val dbItem = repo.loadBagItemById(item.id)
        if (dbItem != null) {
            dbItem.count += 1
            repo.updateBagItemModel(item = dbItem)
        } else {
            repo.saveBagItemModel(BagItemModel(dish = item, count = 1))
        }
    }
}