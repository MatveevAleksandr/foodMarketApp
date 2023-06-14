package com.example.foodmarketapp.app

import android.app.Application
import androidx.room.Room
import com.example.data.AppDataBase
import com.example.foodmarketapp.di.AppComponent
import com.example.foodmarketapp.di.DaggerAppComponent

class App : Application() {
    private lateinit var dataBase: AppDataBase
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        dataBase = Room.databaseBuilder(
            applicationContext, AppDataBase::class.java, "food_market"
        ).build()
        appComponent = DaggerAppComponent.factory().create(this)
    }

    fun getDataBase(): AppDataBase{
        return dataBase
    }
}