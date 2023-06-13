package com.example.data.dish.storage

import com.example.data.dish.model.DishListAPIStorageModel
import com.example.data.dish.retrofit.DishRetrofit
import com.example.domain.model.DishModel
import com.example.domain.model.DishStorageAnswerModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

private const val BASE_URL = "https://run.mocky.io/v3/"

class DishAPIStorageImpl : DishStorage {
    override fun loadDishList(): DishStorageAnswerModel {
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build().create<DishRetrofit>()
        val listCall = retrofit.getList().execute()
        return convertAPIModelToStorageAnswerModel(apiCall = listCall)
    }

    private fun convertAPIModelToStorageAnswerModel(apiCall: Response<DishListAPIStorageModel>): DishStorageAnswerModel {
        val isError = apiCall.code() != 200
        val errorMsg = if (isError) apiCall.errorBody().toString() else ""
        val dishList = if (isError) mutableListOf() else apiCall.body()?.dishList?.map {
            DishModel(
                id = it.id,
                name = it.name,
                price = it.price,
                weight = it.weight,
                description = it.description,
                image_url = it.image_url,
                tags = it.tags
            )
        }?.toMutableList()
        return DishStorageAnswerModel(
            isError = isError, errorMsg = errorMsg, dishList = dishList
        )
    }
}