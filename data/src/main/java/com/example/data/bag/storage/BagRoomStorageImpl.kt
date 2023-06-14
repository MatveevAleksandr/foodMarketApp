package com.example.data.bag.storage

import com.example.data.bag.storage.room.BagDao
import com.example.data.bag.storage.room.BagEntity
import com.example.domain.model.BagItemModel
import com.example.domain.model.DishModel

class BagRoomStorageImpl(private val bagDao: BagDao) : BagStorage {
    override fun updateBagItemModel(item: BagItemModel) {
        bagDao.update(convertBagItemModelToEntity(item))
    }

    override fun deleteBagItemModel(item: BagItemModel) {
        bagDao.delete(convertBagItemModelToEntity(item))
    }

    override fun saveBagItemModel(item: BagItemModel) {
        bagDao.insert(convertBagItemModelToEntity(item))
    }

    override fun loadAllBagItemModel(): List<BagItemModel>? {
        val result = bagDao.getAll()
        return if (result == null) null
        else bagDao.getAll()!!.map { convertEntityToBagItemModel(it!!) }.toMutableList()
    }

    override fun loadBagItemById(id: Int): BagItemModel? {
        val result = bagDao.getById(id)
        return if (result == null) null
        else convertEntityToBagItemModel(bagDao.getById(id)!!)
    }

    private fun convertEntityToBagItemModel(entity: BagEntity): BagItemModel {
        return BagItemModel(
            dish = DishModel(
                id = entity.id,
                name = entity.name,
                price = entity.price,
                weight = entity.weight,
                description = entity.description,
                image_url = entity.image_url,
                tags = listOf()
            ), count = entity.count
        )
    }

    private fun convertBagItemModelToEntity(item: BagItemModel): BagEntity {
        return BagEntity(
            id = item.dish.id,
            name = item.dish.name,
            price = item.dish.price,
            weight = item.dish.weight,
            description = item.dish.description,
            image_url = item.dish.image_url,
            count = item.count,
        )
    }
}