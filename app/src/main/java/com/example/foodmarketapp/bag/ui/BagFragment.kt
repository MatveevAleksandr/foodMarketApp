package com.example.foodmarketapp.bag.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.data.bag.repository.BagRepositoryImpl
import com.example.data.bag.storage.BagRoomStorageImpl
import com.example.domain.usecases.LoadAllBagUseCase
import com.example.domain.usecases.MinusItemInBagUseCase
import com.example.domain.usecases.PlusItemInBagUseCase
import com.example.foodmarketapp.R
import com.example.foodmarketapp.app.App
import com.example.foodmarketapp.bag.adapters.BagRecyclerAdapter
import com.example.foodmarketapp.bag.viewmodel.BagViewModel
import com.example.foodmarketapp.bag.viewmodel.BagViewModelFactory
import kotlinx.coroutines.launch

class BagFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val storage =
            BagRoomStorageImpl((requireActivity().application as App).getDataBase().bagDao())
        val repository = BagRepositoryImpl(storage = storage)
        val viewModel by viewModels<BagViewModel> {
            BagViewModelFactory(
                minusItemInBagUseCase = MinusItemInBagUseCase(repo = repository),
                plusItemInBagUseCase = PlusItemInBagUseCase(repo = repository),
                loadAllBagUseCase = LoadAllBagUseCase(repo = repository)
            )
        }
        val bagRecycler = view.findViewById<RecyclerView>(R.id.bagRecyclerView)
        val btnPay: Button = view.findViewById(R.id.btnPay)
        bagRecycler.layoutManager = LinearLayoutManager(activity)
        viewModel.getBagItemListData().observe(this) { list ->
            if (list == null) {
                bagRecycler.adapter = null
                btnPay.isEnabled = false
            } else {
                bagRecycler.adapter = BagRecyclerAdapter(bagItemList = list, plusClick = {
                    viewLifecycleOwner.lifecycleScope.launch {
                        viewModel.plusItem(it)
                    }
                }, minusClick = {
                    viewLifecycleOwner.lifecycleScope.launch {
                        viewModel.minusItem(it)
                    }
                })
                btnPay.text = "Оплатить ${viewModel.getSumPrice()} ₽"
                btnPay.isEnabled = true
            }
        }
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadAllBag()
        }
    }
}