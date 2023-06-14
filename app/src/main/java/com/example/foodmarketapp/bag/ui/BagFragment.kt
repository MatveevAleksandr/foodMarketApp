package com.example.foodmarketapp.bag.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodmarketapp.R
import com.example.foodmarketapp.app.App
import com.example.foodmarketapp.bag.adapters.BagRecyclerAdapter
import com.example.foodmarketapp.bag.viewmodel.BagViewModel
import com.example.foodmarketapp.bag.viewmodel.BagViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class BagFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: BagViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_bag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity?.applicationContext as App).appComponent.injectBagFragment(this)
        val viewModel = ViewModelProvider(this, viewModelFactory)[BagViewModel::class.java]
        val bagRecycler = view.findViewById<RecyclerView>(R.id.bagRecyclerView)
        val btnPay: Button = view.findViewById(R.id.btnPay)
        bagRecycler.layoutManager = LinearLayoutManager(activity)
        viewModel.getBagItemListData().observe(this) { list ->
            if (list.isNullOrEmpty()) {
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
                btnPay.isEnabled = true
            }
            btnPay.text = "Оплатить ${viewModel.getSumPrice()} ₽"
        }
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadAllBag()
        }
    }
}