package com.example.domain.usecases

import com.example.domain.model.BagItemModel
import com.example.domain.repository.BagRepository

class LoadAllBagUseCase(private val repo: BagRepository) {
    fun execute(): List<BagItemModel>?{
        return repo.loadAllBagItemModel()
    }
}