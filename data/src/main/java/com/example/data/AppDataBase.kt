package com.example.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.bag.storage.room.BagDao
import com.example.data.bag.storage.room.BagEntity

@Database(entities = [BagEntity::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun bagDao(): BagDao
}