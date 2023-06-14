package com.example.foodmarketapp.di

import com.example.data.bag.repository.BagRepositoryImpl
import com.example.data.bag.storage.BagRoomStorageImpl
import com.example.data.bag.storage.BagStorage
import com.example.data.bag.storage.room.BagDao
import com.example.data.category.repository.CategoryRepositoryImpl
import com.example.data.category.storage.CategoryAPIStorageImpl
import com.example.data.category.storage.CategoryStorage
import com.example.data.dish.repository.DishRepositoryImpl
import com.example.data.dish.storage.DishStorage
import com.example.data.dish.storage.retrofit.DishAPIStorageImpl
import com.example.domain.repository.BagRepository
import com.example.domain.repository.CategoryRepository
import com.example.domain.repository.DishRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideDishStorage(): DishStorage {
        return DishAPIStorageImpl()
    }

    @Provides
    @Singleton
    fun provideCategoryStorage(): CategoryStorage {
        return CategoryAPIStorageImpl()
    }

    @Provides
    @Singleton
    fun provideBagStorage(bagDao: BagDao): BagStorage {
        return BagRoomStorageImpl(bagDao)
    }

    @Provides
    @Singleton
    fun provideDishRepository(storage: DishStorage): DishRepository {
        return DishRepositoryImpl(dishStorage = storage)
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(storage: CategoryStorage): CategoryRepository {
        return CategoryRepositoryImpl(categoryStorage = storage)
    }

    @Provides
    @Singleton
    fun provideBagRepository(storage: BagStorage): BagRepository {
        return BagRepositoryImpl(storage = storage)
    }
}