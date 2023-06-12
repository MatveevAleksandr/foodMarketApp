package com.example.foodmarketapp.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.category.repository.CategoryRepositoryImpl
import com.example.data.category.storage.CategoryAPIStorageImpl
import com.example.domain.usecases.CategoryCardClickUseCase
import com.example.domain.usecases.GetCategoryListUseCase
import com.example.foodmarketapp.R
import com.example.foodmarketapp.home.adapters.CategoryRecyclerAdapter
import com.example.foodmarketapp.home.viewmodel.HomeViewModel
import com.example.foodmarketapp.home.viewmodel.HomeViewModelFactory
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private val categoryAPIStorage = CategoryAPIStorageImpl()
    private val categoryRepository = CategoryRepositoryImpl(categoryStorage = categoryAPIStorage)
    private val viewModel by viewModels<HomeViewModel> {
        HomeViewModelFactory(
            categoryCardClickUseCase = CategoryCardClickUseCase(),
            getCategoryListUseCase = GetCategoryListUseCase(categoryRepository = categoryRepository)
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

        viewModel.getHomeFragmentState().observe(this) {
            when (it) {
                is HomeFragmentStateSuccessfulLoad -> {
                    categoryRecyclerView.adapter = CategoryRecyclerAdapter(it.categoryList)
                }
                is HomeFragmentStateErrorLoad -> {
                    Toast.makeText(
                        activity, "Ошибка, ${it.errorMsg}", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        val sdf = SimpleDateFormat("dd MMMM, yyyy", Locale.getDefault())
        view.findViewById<TextView>(R.id.current_date_time_textview).text =
            sdf.format(Calendar.getInstance().time)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadCategoryList()
        }
    }
}