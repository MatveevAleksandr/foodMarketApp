package com.example.foodmarketapp.di

import com.example.domain.repository.BagRepository
import com.example.domain.repository.CategoryRepository
import com.example.domain.repository.DishRepository
import com.example.domain.usecases.*
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideGetCategoryListUseCase(repository: CategoryRepository): GetCategoryListUseCase {
        return GetCategoryListUseCase(categoryRepository = repository)
    }

    @Provides
    fun provideGetDishListByCategoryIDUseCase(repository: DishRepository): GetDishListByCategoryIDUseCase {
        return GetDishListByCategoryIDUseCase(dishRepository = repository)
    }

    @Provides
    fun provideGetDishListByTagUseCase(repository: DishRepository): GetDishListByTagUseCase {
        return GetDishListByTagUseCase(dishRepository = repository)
    }

    @Provides
    fun provideLoadAllBagUseCase(repository: BagRepository): LoadAllBagUseCase {
        return LoadAllBagUseCase(repo = repository)
    }

    @Provides
    fun provideMinusItemInBagUseCase(repository: BagRepository): MinusItemInBagUseCase {
        return MinusItemInBagUseCase(repo = repository)
    }

    @Provides
    fun providePlusItemInBagUseCase(repository: BagRepository): PlusItemInBagUseCase {
        return PlusItemInBagUseCase(repo = repository)
    }
}