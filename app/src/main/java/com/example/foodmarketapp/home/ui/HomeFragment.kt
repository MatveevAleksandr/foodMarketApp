package com.example.foodmarketapp.home.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.usecases.CategoryCardClickUseCase
import com.example.foodmarketapp.R
import com.example.foodmarketapp.home.adapters.CategoryRecyclerAdapter
import com.example.foodmarketapp.home.viewmodel.HomeViewModel
import com.example.foodmarketapp.home.viewmodel.HomeViewModelFactory

class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel> {
        HomeViewModelFactory(
            CategoryCardClickUseCase()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryRecyclerView = view.findViewById<RecyclerView>(R.id.categoryRecyclerView)
        categoryRecyclerView.layoutManager = LinearLayoutManager(activity)
        categoryRecyclerView.adapter = CategoryRecyclerAdapter(viewModel.getCategoryList())
    }
}