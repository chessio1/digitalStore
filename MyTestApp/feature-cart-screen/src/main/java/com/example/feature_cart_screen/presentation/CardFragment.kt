package com.example.feature_cart_screen.presentation

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core.utils.utils.autoCleared
import com.example.feature_cart_screen.R
import com.example.feature_cart_screen.databinding.FragmentCardBinding
import com.example.feature_cart_screen.presentation.adapters.CartAdapter
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.agladkov.uitls.navigation.NavCommand
import ru.agladkov.uitls.navigation.NavCommands
import ru.agladkov.uitls.navigation.navigate


class CardFragment : Fragment(R.layout.fragment_card) {

    private val vm: CartViewModel by viewModel()
    private val binding: FragmentCardBinding by viewBinding()
    private var cartAdapter by autoCleared<CartAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadData()

    }

    private fun loadData() {
        vm.loadData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initRecyclerView()
        setObservers()

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun initAdapter() {
        cartAdapter = CartAdapter() { count, prices ->
            binding.totalCountTextView.text = getTotal(count, prices)
        }
    }

    private fun getTotal(
        count: List<Int>,
        prices: List<Int>
    ): String {
        val sum = (count.mapIndexed { index, count ->
            count * prices[index]
        }).sum().toString()
        val needFormat = "$${sum.format("%,d")} us"
        return needFormat
    }

    private fun initRecyclerView() {
        binding.cartRecyclerView.apply {
            adapter = cartAdapter
            layoutManager =
                LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
            setHasFixedSize(true)
        }
    }

    private fun setObservers() {
        lifecycleScope.launchWhenCreated {
            vm.cartData
                .collect {
                    if (it == null) return@collect
                    cartAdapter.setNewList(it.basket)
                    binding.deliveryStatusTextView.text =
                        it.delivery.replaceFirstChar { it.uppercase() }
                    binding.totalCountTextView.text =
                        getTotal(it.basket.map { 1 }, it.basket.map { it.price })
                    binding.progress.isVisible = false
                }
        }

    }

}