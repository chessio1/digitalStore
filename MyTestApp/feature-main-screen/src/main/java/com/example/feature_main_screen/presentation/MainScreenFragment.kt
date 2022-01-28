package com.example.feature_main_screen.presentation

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.core.utils.textChangedFlow
import com.example.feature_main_screen.R
import com.example.feature_main_screen.databinding.FragmentMainScreenBinding
import com.example.feature_main_screen.databinding.LayoutFotterBinding
import com.example.feature_main_screen.databinding.SearchEditTextBinding
import com.example.feature_main_screen.presentation.adapters.BestSalesAdapter
import com.example.feature_main_screen.presentation.adapters.DeviceSelectAdapter
import com.example.feature_main_screen.presentation.adapters.HotSalesAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.agladkov.uitls.navigation.NavCommand
import ru.agladkov.uitls.navigation.NavCommands
import ru.agladkov.uitls.navigation.navigate

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
                    vm.searchPhones(it)
                }
        }

        vm.remoteMainScreen.observe(viewLifecycleOwner) {
            // задаём размер корзины
            val countOfCarts = 2
            if (countOfCarts > 0) {
                fotterBindig.countOfCartTextView.text = countOfCarts.toString()
                fotterBindig.countOfCartTextView.isVisible = true
            }
            hotSalesAdapter.setNewList(it)
            bestSalesAdapter.setNewList(it.best_seller)
        }

        fotterBindig.fotterCartButton.setOnClickListener {
            goToCart()
        }

        binding.filterDialog.setOnClickListener {
            FilterDialog().show(childFragmentManager, "filterDialog")
        }

        binding.mapSelectLayout.setOnClickListener {
            navigateTo("https://mysite.com/map")
        }

    }

    private fun goToCart() {
        navigateTo("https://mysite.com/cart")
    }

    private fun navigateTo(deepLink: String) {
        navigate(
            NavCommand(
                NavCommands.DeepLink(
                    url = Uri.parse(deepLink),
                    isModal = false,
                    isSingleTop = true
                )
            )
        )
    }

    private fun initData() {

        vm.getMainScreen()
        vm.getToken()
        initAdapters()
        initFilterDialogListener()

    }

    private fun initFilterDialogListener() {
        childFragmentManager.setFragmentResultListener(
            "filterOptions",
            this
        ) { _, bundle ->
            val brand = bundle.getString("brand")
            val price = bundle.getInt("price")
            val size = bundle.getDoubleArray("size") ?: doubleArrayOf(1.0, 1.0)
            if (size.size == 1) {
                if (size.first() == 3.4) {
                    showToast("selected $brand price: $$price size: less than ${size.first()} inches")
                } else {
                    showToast("selected $brand price: $$price size: larger than ${size.first()} inches")
                }
            } else {
                showToast("selected $brand price: \$$price size: between ${size.first()} and ${size.last()} inches")
            }
        }
    }

    private fun initAdapters() {
        _deviceSelectAdapter = DeviceSelectAdapter()
        _hotSalesAdapter = HotSalesAdapter()
        _bestSalesAdapter = BestSalesAdapter {
            navigate(
                NavCommand(
                    target = NavCommands.DeepLink(
                        Uri.parse(
                            "https://mysite.com/details/$it"
                        ),
                        false,
                        true
                    )
                )
            )
        }
    }

    private fun showToast(toastText: String) {
        Toast.makeText(
            requireContext(),
            toastText,
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _hotSalesAdapter = null
        _bestSalesAdapter = null
        _deviceSelectAdapter = null
    }

}