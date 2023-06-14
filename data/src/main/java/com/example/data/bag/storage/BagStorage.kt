package com.example.data.bag.storage

import com.example.domain.model.BagItemModel

interface BagStorage {
    fun updateBagItemModel(item: BagItemModel)
    fun deleteBagItemModel(item: BagItemModel)
    fun saveBagItemModel(item: BagItemModel)
    fun loadAllBagItemModel(): List<BagItemModel>?
    fun loadBagItemById(id: Int): BagItemModel?
}