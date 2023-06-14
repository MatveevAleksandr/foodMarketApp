package com.example.data.bag.storage.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BagEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("price") val price: Int,
    @ColumnInfo("weight") val weight: Int,
    @ColumnInfo("description") val description: String,
    @ColumnInfo("image_url") val image_url: String?,
    @ColumnInfo("count_in_bag") val count: Int
)