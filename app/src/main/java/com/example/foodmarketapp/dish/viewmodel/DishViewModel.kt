package com.example.foodmarketapp.dish.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.usecases.GetDishListByCategoryIDUseCase
import com.example.domain.usecases.GetDishListByTagUseCase
import com.example.foodmarketapp.dish.ui.DishFragmentState
import com.example.foodmarketapp.dish.ui.DishFragmentStateErrorLoad
import com.example.foodmarketapp.dish.ui.DishFragmentStateSuccessfulLoad
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DishViewModel(
    private val getDishListByCategoryIDUseCase: GetDishListByCategoryIDUseCase,
    private val getDishListByTagUseCase: GetDishListByTagUseCase
) : ViewModel() {

    private var dishFragmentState = MutableLiveData<DishFragmentState>()

    private fun setDishFragmentState(state: DishFragmentState) {
        dishFragmentState.value = state
    }

    fun getDishFragmentState(): LiveData<DishFragmentState> {
        return dishFragmentState
    }

    suspend fun loadDishListByCategoryID(categoryID: Int) {
        coroutineScope {
            launch(Dispatchers.IO) {
                val result = getDishListByCategoryIDUseCase.execute(categoryID = categoryID)
                withContext(Dispatchers.Main) {
                    val state = if (result.isError) {
                        DishFragmentStateErrorLoad(errorMsg = result.errorMsg)
                    } else {
                        DishFragmentStateSuccessfulLoad(dishList = result.dishList!!)
                    }
                    setDishFragmentState(state)
                }
            }
        }
    }

    suspend fun loadDishListByTag(categoryID: Int, tag: String) {
        coroutineScope {
            launch(Dispatchers.IO) {
                val result = getDishListByTagUseCase.execute(categoryID = categoryID, tag = tag)
                withContext(Dispatchers.Main) {
                    val state = if (result.isError) {
                        DishFragmentStateErrorLoad(errorMsg = result.errorMsg)
                    } else {
                        DishFragmentStateSuccessfulLoad(dishList = result.dishList!!)
                    }
                    setDishFragmentState(state)
                }
            }
        }
    }
}