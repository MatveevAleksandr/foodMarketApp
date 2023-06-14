package com.example.domain.repository

import com.example.domain.model.BagItemModel

interface BagRepository {
    fun updateBagItemModel(item: BagItemModel)
    fun deleteBagItemModel(item: BagItemModel)
    fun saveBagItemModel(item: BagItemModel)
    fun loadAllBagItemModel(): List<BagItemModel>?
    fun loadBagItemById(id: Int): BagItemModel?
}