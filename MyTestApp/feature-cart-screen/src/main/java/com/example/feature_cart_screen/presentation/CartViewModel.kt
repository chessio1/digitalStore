package com.example.feature_cart_screen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_cart_screen.data.model.RemoteCart
import com.example.feature_cart_screen.domain.repository.CartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartViewModel(private val repository: CartRepository):ViewModel() {

    private val _cartData = MutableStateFlow<RemoteCart?>(null)
    val cartData = _cartData.asStateFlow()

    fun loadData() {
        viewModelScope.launch {
            _cartData.value = repository.loadCart()
        }
    }

}
