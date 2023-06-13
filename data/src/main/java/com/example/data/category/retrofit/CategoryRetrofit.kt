package com.example.data.category.retrofit


import com.example.data.category.model.CategoryListAPIStorageModel
import retrofit2.Call
import retrofit2.http.GET

interface CategoryRetrofit {
    @GET("058729bd-1402-4578-88de-265481fd7d54/")
    fun getList(): Call<CategoryListAPIStorageModel>
}