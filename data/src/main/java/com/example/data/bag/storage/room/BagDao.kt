package com.example.data.bag.storage.room

import androidx.room.*

@Dao
interface BagDao {
    @Query("SELECT * FROM BagEntity")
    fun getAll(): List<BagEntity?>?

    @Query("SELECT * FROM BagEntity WHERE id = :id")
    fun getById(id: Int): BagEntity?

    @Insert
    fun insert(employee: BagEntity)

    @Update
    fun update(employee: BagEntity)

    @Delete
    fun delete(employee: BagEntity)
}