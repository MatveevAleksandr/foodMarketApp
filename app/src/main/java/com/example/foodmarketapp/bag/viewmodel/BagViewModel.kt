package com.example.foodmarketapp.bag.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.BagItemModel
import com.example.domain.model.DishModel
import com.example.domain.usecases.LoadAllBagUseCase
import com.example.domain.usecases.MinusItemInBagUseCase
import com.example.domain.usecases.PlusItemInBagUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BagViewModel(
    private val plusItemInBagUseCase: PlusItemInBagUseCase,
    private val minusItemInBagUseCase: MinusItemInBagUseCase,
    private val loadAllBagUseCase: LoadAllBagUseCase
) : ViewModel() {
    private val bagItemListData = MutableLiveData<List<BagItemModel>?>()

    private fun setBagItemListData(list: List<BagItemModel>?) {
        bagItemListData.value = list
    }

    fun getBagItemListData(): LiveData<List<BagItemModel>?> {
        return bagItemListData
    }

    suspend fun plusItem(item: DishModel) {
        coroutineScope {
            launch(Dispatchers.IO) {
                plusItemInBagUseCase.execute(item = item)
                val result = loadAllBagUseCase.execute()
                withContext(Dispatchers.Main) {
                    setBagItemListData(result)
                }
            }
        }
    }

    suspend fun minusItem(itemID: Int) {
        coroutineScope {
            launch(Dispatchers.IO) {
                minusItemInBagUseCase.execute(itemID = itemID)
                val result = loadAllBagUseCase.execute()
                withContext(Dispatchers.Main) {
                    setBagItemListData(result)
                }
            }
        }
    }

    suspend fun loadAllBag() {
        coroutineScope {
            launch(Dispatchers.IO) {
                val result = loadAllBagUseCase.execute()
                withContext(Dispatchers.Main) {
                    setBagItemListData(result)
                }
            }
        }
    }

    fun getSumPrice(): Int {
        var cnt = 0
        getBagItemListData().value?.forEach {
            cnt += it.dish.price * it.count
        }
        return cnt
    }
}