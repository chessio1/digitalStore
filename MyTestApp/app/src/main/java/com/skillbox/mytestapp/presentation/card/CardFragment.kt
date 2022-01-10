package com.skillbox.mytestapp.presentation.card

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.mytestapp.R
import com.skillbox.mytestapp.databinding.FragmentCardBinding

import com.skillbox.mytestapp.utils.autoCleared
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

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
                    Timber.d("Hello")
                    if (it == null) return@collect
                    cartAdapter.setNewList(it.basket)
                    binding.deliveryStatusTextView.text = it.Delivery.replaceFirstChar { it.uppercase() }
                    binding.totalCountTextView.text =
                        getTotal(it.basket.map { 1 }, it.basket.map { it.price })
                    binding.progress.isVisible = false
                }
        }

    }

}