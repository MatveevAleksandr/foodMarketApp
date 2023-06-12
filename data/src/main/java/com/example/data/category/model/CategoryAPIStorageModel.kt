package com.example.data.category.model

import com.google.gson.annotations.SerializedName

data class CategoryListAPIStorageModel(
    @SerializedName("сategories") val cat: List<CategoryAPIStorageModel>
)

data class CategoryAPIStorageModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image_url") val image_url: String
)