package com.skillbox.mytestapp.presentation.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skillbox.mytestapp.data.CartRepository
import com.skillbox.mytestapp.data.models.myCard.RemoteCart
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartViewModel(private val repository:CartRepository):ViewModel() {

    private val _cartData = MutableStateFlow<RemoteCart?>(null)
    val cartData = _cartData.asStateFlow()

    fun loadData() {
        viewModelScope.launch {
            _cartData.value = repository.loadCart()
        }
    }

}
