package com.example.data.dish.storage.retrofit

import com.example.data.dish.model.DishListAPIStorageModel
import retrofit2.Call
import retrofit2.http.GET

interface DishRetrofit {
    @GET("c7a508f2-a904-498a-8539-09d96785446e/")
    fun getList(): Call<DishListAPIStorageModel >
}