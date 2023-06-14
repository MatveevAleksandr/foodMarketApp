package com.example.domain.usecases

import com.example.domain.repository.BagRepository

class MinusItemInBagUseCase(private val repo: BagRepository) {
    fun execute(itemID: Int) {
        val dbItem = repo.loadBagItemById(itemID)
        if (dbItem != null) {
            dbItem.count -= 1
            if (dbItem.count <= 0) repo.deleteBagItemModel(item = dbItem)
            else repo.updateBagItemModel(item = dbItem)
        }
    }
}