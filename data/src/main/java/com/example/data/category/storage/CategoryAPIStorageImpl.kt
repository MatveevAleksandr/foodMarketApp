package com.example.data.category.storage

import android.util.Log
import com.example.data.category.model.CategoryAPIStorageModel
import com.example.data.category.model.CategoryListAPIStorageModel
import com.example.domain.model.CategoryStorageAnswerModel
import com.example.data.category.retrofit.CategoryRetrofit
import com.example.domain.model.CategoryModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

private const val BASE_URL = "https://run.mocky.io/v3/"

class CategoryAPIStorageImpl : CategoryStorage {
    override fun loadCategoryList(): CategoryStorageAnswerModel {
        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build().create<CategoryRetrofit>()
        val listCall = retrofit.getList().execute()
        Log.d("AAA_AAA",listCall.toString())
        return convertAPIModelToStorageAnswerModel(apiCall = listCall)
    }

    private fun convertAPIModelToStorageAnswerModel(apiCall: Response<CategoryListAPIStorageModel>): CategoryStorageAnswerModel {
        val isError = apiCall.code() != 200
        val errorMsg = if (isError) apiCall.errorBody().toString() else ""
        val categoryList = if (isError) listOf() else apiCall.body()?.cat?.map {
            CategoryModel(id = it.id, name = it.name, image_url = it.image_url)
        }?.toMutableList()
        return CategoryStorageAnswerModel(
            isError = isError, errorMsg = errorMsg, categoryList = categoryList
        )
    }
}