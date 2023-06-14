package com.example.foodmarketapp.dish.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmarketapp.R
import com.example.foodmarketapp.app.App
import com.example.foodmarketapp.dish.adapters.DishRecyclerAdapter
import com.example.foodmarketapp.dish.adapters.DishTypeRecyclerAdapter
import com.example.foodmarketapp.dish.viewmodel.DishViewModel
import com.example.foodmarketapp.dish.viewmodel.DishViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

const val CATEGORY_ID_BUNDLE = "categoryID"
const val CATEGORY_NAME_BUNDLE = "categoryName"

class DishFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: DishViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dish, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity?.applicationContext as App).appComponent.injectDishFragment(this)
        val viewModel = ViewModelProvider(this, viewModelFactory)[DishViewModel::class.java]
        val categoryID = arguments?.getInt(CATEGORY_ID_BUNDLE) ?: -1
        view.findViewById<TextView>(R.id.dish_category_title).text =
            arguments?.getString(CATEGORY_NAME_BUNDLE)
        view.findViewById<CardView>(R.id.backCardView).setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                .navigate(R.id.navigation_home)
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
                    dishRecycler.adapter = DishRecyclerAdapter(state.dishList, addClick = {
                        viewLifecycleOwner.lifecycleScope.launch {
                            viewModel.addItemToBag(it)
                        }
                    })
                }
                is DishFragmentStateErrorLoad -> {
                    Toast.makeText(
                        activity, "Ошибка, ${state.errorMsg}", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        val nav = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        nav.clearBackStack(R.id.navigation_home)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadDishListByCategoryID(categoryID = categoryID)
        }
    }
}