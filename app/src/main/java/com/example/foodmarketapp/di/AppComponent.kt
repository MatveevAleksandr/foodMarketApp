package com.example.foodmarketapp.di

import android.content.Context
import com.example.foodmarketapp.MainActivity
import com.example.foodmarketapp.bag.ui.BagFragment
import com.example.foodmarketapp.dish.ui.DishFragment
import com.example.foodmarketapp.home.ui.HomeFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, DomainModule::class, AppModule::class])
interface AppComponent {
    fun injectHomeFragment(homeFragment: HomeFragment)
    fun injectDishFragment(dishFragment: DishFragment)
    fun injectBagFragment(bagFragment: BagFragment)
    @Component.Factory
    interface AppComponentFactory{
        fun create(@BindsInstance context: Context): AppComponent
    }
}