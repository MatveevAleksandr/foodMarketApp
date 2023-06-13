package com.example.data.dish.model

import com.google.gson.annotations.SerializedName

data class DishListAPIStorageModel(
    @SerializedName("dishes") val dishList: List<DishAPIStorageModel>
)

data class DishAPIStorageModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("description") val description: String,
    @SerializedName("image_url") val image_url: String?,
    @SerializedName("tegs") val tags: List<String>
)