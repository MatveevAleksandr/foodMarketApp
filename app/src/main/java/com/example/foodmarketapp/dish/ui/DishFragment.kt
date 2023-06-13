package com.example.foodmarketapp.dish.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.dish.repository.DishRepositoryImpl
import com.example.data.dish.storage.DishAPIStorageImpl
import com.example.domain.usecases.GetDishListByCategoryIDUseCase
import com.example.domain.usecases.GetDishListByTagUseCase
import com.example.foodmarketapp.R
import com.example.foodmarketapp.dish.adapters.DishRecyclerAdapter
import com.example.foodmarketapp.dish.adapters.DishTypeRecyclerAdapter
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
        view.findViewById<CardView>(R.id.backCardView).setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).popBackStack()
        }

        val dishTypeRecycler = view.findViewById<RecyclerView>(R.id.dishTypeRecyclerView)
        val dishRecycler = view.findViewById<RecyclerView>(R.id.dishRecyclerView)
        dishTypeRecycler.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        dishRecycler.layoutManager = GridLayoutManager(activity, 3)

        viewModel.getDishFragmentState().observe(this) { state ->
            when (state) {
                is DishFragmentStateSuccessfulLoad -> {
                    dishTypeRecycler.adapter = DishTypeRecyclerAdapter(
                        dishTypeList = state.dishTypesList,
                        dishTypeCLick = {
                            viewLifecycleOwner.lifecycleScope.launch {
                                viewModel.loadDishListByTag(categoryID, it)
                            }
                        })
                    dishRecycler.adapter = DishRecyclerAdapter(state.dishList)
                }
                is DishFragmentStateErrorLoad -> {
                    Toast.makeText(
                        activity, "Ошибка, ${state.errorMsg}", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadDishListByCategoryID(categoryID = categoryID)
        }
    }
}