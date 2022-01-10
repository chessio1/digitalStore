package com.skillbox.mytestapp.presentation.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.mytestapp.R
import com.skillbox.mytestapp.databinding.FragmentMainScreenBinding
import com.skillbox.mytestapp.databinding.LayoutFotterBinding
import com.skillbox.mytestapp.databinding.SearchEditTextBinding

import com.skillbox.mytestapp.presentation.main.adapters.BestSalesAdapter
import com.skillbox.mytestapp.presentation.main.adapters.DeviceSelectAdapter
import com.skillbox.mytestapp.presentation.main.adapters.HotSalesAdapter
import com.skillbox.mytestapp.utils.textChangedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {

    private val vm by viewModel<MainScreenViewModel>()
    private val binding: FragmentMainScreenBinding by viewBinding()
    private val searchBinding: SearchEditTextBinding by viewBinding()
    private val fotterBindig: LayoutFotterBinding by viewBinding()

    private var _hotSalesAdapter: HotSalesAdapter? = null
    private val hotSalesAdapter: HotSalesAdapter
        get() = _hotSalesAdapter!!

    private var _bestSalesAdapter: BestSalesAdapter? = null
    private val bestSalesAdapter: BestSalesAdapter
        get() = _bestSalesAdapter!!

    private var _deviceSelectAdapter: DeviceSelectAdapter? = null
    private val deviceSelectAdapter: DeviceSelectAdapter
        get() = _deviceSelectAdapter!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initViewPagers()
    }

    private fun initViewPagers() {
        binding.hotSalesViewPager.apply {
            adapter = hotSalesAdapter
            currentItem = 0
        }

        binding.bestSellerRecyclerView.apply {
            adapter = bestSalesAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)

            setHasFixedSize(true)
        }

        binding.deviceSelectRecyclerView.apply {
            adapter = deviceSelectAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }

    }

    private fun initListeners() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            searchBinding.searchEditText.textChangedFlow()
                .onStart { emit("") }
                .debounce(500)
                .distinctUntilChanged()
                .collect {
                    Timber.d("${bestSalesAdapter.itemCount}")
                }
        }

        vm.remoteMainScreen.observe(viewLifecycleOwner) {
            Timber.d(it.toString())
            val countOfCarts = 2
            if (countOfCarts>0){
                fotterBindig.countOfCartTextView.text = countOfCarts.toString()
                fotterBindig.countOfCartTextView.isVisible = true
            }


            hotSalesAdapter.setNewList(it)
            bestSalesAdapter.setNewList(it.best_seller)
        }

        fotterBindig.fotterCartButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainScreenFragment_to_cardFragment)
        }

        binding.filterDialog.setOnClickListener {
            lifecycleScope.launch {
                FilterDialog().show(childFragmentManager, "filterDialog")
            }
        }



    }

    private fun initData() {
        vm.getMainScreen()
        _hotSalesAdapter = HotSalesAdapter()
        _bestSalesAdapter = BestSalesAdapter {
            val action = MainScreenFragmentDirections.actionMainScreenFragmentToDetailsFragment(it)
            findNavController().navigate(action)
        }
        _deviceSelectAdapter = DeviceSelectAdapter()

        childFragmentManager.setFragmentResultListener("filterOptions",this) { requestKey, bundle ->
            val brand = bundle.getString("brand")
            val price = bundle.getInt("price")
            val size = bundle.getDoubleArray("size") ?: doubleArrayOf(1.0,1.0)
            Timber.d(price.toString())
            if (size.size == 1){
                if (size.first() == 3.4){
                    Toast.makeText(requireContext(), "selected $brand price: $$price size: less than ${size.first()} inches", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(requireContext(), "selected $brand price: $$price size: larger than ${size.first()} inches", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(requireContext(), "selected $brand price: $$price size: between ${size.first()} and ${size.last()} inches", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _hotSalesAdapter = null
        _bestSalesAdapter = null
    }

}