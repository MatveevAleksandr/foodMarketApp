package com.example.data.bag.repository

import com.example.data.bag.storage.BagStorage
import com.example.domain.model.BagItemModel
import com.example.domain.repository.BagRepository

class BagRepositoryImpl(private val storage: BagStorage): BagRepository {
    override fun updateBagItemModel(item: BagItemModel) {
        storage.updateBagItemModel(item)
    }

    override fun deleteBagItemModel(item: BagItemModel) {
        storage.deleteBagItemModel(item)
    }

    override fun saveBagItemModel(item: BagItemModel) {
        storage.saveBagItemModel(item)
    }

    override fun loadAllBagItemModel(): List<BagItemModel>? {
        return storage.loadAllBagItemModel()
    }

    override fun loadBagItemById(id: Int): BagItemModel? {
        return storage.loadBagItemById(id)
    }
}