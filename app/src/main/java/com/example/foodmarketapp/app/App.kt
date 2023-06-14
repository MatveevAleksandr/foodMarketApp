package com.example.foodmarketapp.app

import android.app.Application
import androidx.room.Room
import com.example.data.AppDataBase

class App : Application() {
    private lateinit var dataBase: AppDataBase

    override fun onCreate() {
        super.onCreate()
        dataBase = Room.databaseBuilder(
            applicationContext, AppDataBase::class.java, "food_market"
        ).build()
    }

    fun getDataBase(): AppDataBase{
        return dataBase
    }
}