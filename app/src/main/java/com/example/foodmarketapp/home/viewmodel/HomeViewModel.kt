package com.example.foodmarketapp.home.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.usecases.GetCategoryListUseCase
import com.example.foodmarketapp.home.ui.HomeFragmentState
import com.example.foodmarketapp.home.ui.HomeFragmentStateErrorLoad
import com.example.foodmarketapp.home.ui.HomeFragmentStateSuccessfulLoad
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val getCategoryListUseCase: GetCategoryListUseCase
) : ViewModel() {

    private var homeFragmentState = MutableLiveData<HomeFragmentState>()

    private fun setHomeFragmentState(state: HomeFragmentState){
        homeFragmentState.value = state
    }

    fun getHomeFragmentState(): LiveData<HomeFragmentState> {
        return homeFragmentState
    }

    suspend fun loadCategoryList() {
        coroutineScope {
            launch(Dispatchers.IO) {
                val result = getCategoryListUseCase.execute()
                withContext(Dispatchers.Main){
                    val state = if(result.isError){
                        HomeFragmentStateErrorLoad(errorMsg = result.errorMsg)
                    } else {
                        HomeFragmentStateSuccessfulLoad(categoryList = result.categoryList!!)
                    }
                    setHomeFragmentState(state)
                }
            }
        }
    }
}