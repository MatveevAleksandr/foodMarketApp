package com.example.foodmarketapp.di

import android.content.Context
import androidx.room.Room
import com.example.data.AppDataBase
import com.example.data.bag.storage.room.BagDao
import com.example.domain.usecases.*
import com.example.foodmarketapp.bag.viewmodel.BagViewModelFactory
import com.example.foodmarketapp.dish.viewmodel.DishViewModelFactory
import com.example.foodmarketapp.home.viewmodel.HomeViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule() {
    @Provides
    fun provideHomeModelFactory(
        getCategoryListUseCase: GetCategoryListUseCase
    ): HomeViewModelFactory {
        return HomeViewModelFactory(
            getCategoryListUseCase = getCategoryListUseCase
        )
    }

    @Provides
    fun provideDishModelFactory(
        getDishListByTagUseCase: GetDishListByTagUseCase,
        getDishListByCategoryIDUseCase: GetDishListByCategoryIDUseCase,
        plusItemInBagUseCase: PlusItemInBagUseCase
    ): DishViewModelFactory {
        return DishViewModelFactory(
            getDishListByTagUseCase = getDishListByTagUseCase,
            getDishListByCategoryIDUseCase = getDishListByCategoryIDUseCase,
            plusItemInBagUseCase = plusItemInBagUseCase
        )
    }

    @Provides
    fun provideBagModelFactory(
        plusItemInBagUseCase: PlusItemInBagUseCase,
        minusItemInBagUseCase: MinusItemInBagUseCase,
        loadAllBagUseCase: LoadAllBagUseCase
    ): BagViewModelFactory {
        return BagViewModelFactory(
            plusItemInBagUseCase = plusItemInBagUseCase,
            minusItemInBagUseCase = minusItemInBagUseCase,
            loadAllBagUseCase = loadAllBagUseCase
        )
    }

    @Provides
    fun provideRoomDataBase(context: Context): AppDataBase{
        return Room.databaseBuilder(
            context, AppDataBase::class.java, "food_market"
        ).build()
    }

    @Provides
    fun provideRoomBagDao(db: AppDataBase): BagDao{
        return db.bagDao()
    }
}