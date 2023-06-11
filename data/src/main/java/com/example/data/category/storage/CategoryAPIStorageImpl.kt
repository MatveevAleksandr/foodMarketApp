package com.example.data.category.storage

import com.example.domain.model.CategoryModel

class CategoryAPIStorageImpl: CategoryStorage {
    override fun loadCategoryList(): List<CategoryModel> {
        return (1..10).map {
            CategoryModel(id = it, name = "name $it", image_url = "https://$it")
        }.toMutableList()
    }
}