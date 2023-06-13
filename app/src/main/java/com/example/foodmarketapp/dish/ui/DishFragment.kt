package com.example.foodmarketapp.dish.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.data.dish.repository.DishRepositoryImpl
import com.example.data.dish.storage.DishAPIStorageImpl
import com.example.domain.usecases.GetDishListByCategoryIDUseCase
import com.example.domain.usecases.GetDishListByTagUseCase
import com.example.foodmarketapp.R
import com.example.foodmarketapp.dish.viewmodel.DishViewModel
import com.example.foodmarketapp.dish.viewmodel.DishViewModelFactory
import kotlinx.coroutines.launch

const val CATEGORY_ID_BUNDLE = "categoryID"
const val CATEGORY_NAME_BUNDLE = "categoryName"

class DishFragment : Fragment() {

    private val dishAPIStorage = DishAPIStorageImpl()
    private val dishRepository = DishRepositoryImpl(dishStorage = dishAPIStorage)
    private val viewModel by viewModels<DishViewModel> {
        DishViewModelFactory(
            getDishListByCategoryIDUseCase = GetDishListByCategoryIDUseCase(dishRepository = dishRepository),
            getDishListByTagUseCase = GetDishListByTagUseCase(dishRepository = dishRepository)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dish, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryID = arguments?.getInt(CATEGORY_ID_BUNDLE) ?: -1
        view.findViewById<TextView>(R.id.dish_category_title).text =
            arguments?.getString(CATEGORY_NAME_BUNDLE)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadDishListByCategoryID(categoryID = categoryID)
        }
    }
}