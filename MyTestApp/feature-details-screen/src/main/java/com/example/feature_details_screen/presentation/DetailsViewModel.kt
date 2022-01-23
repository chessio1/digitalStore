package com.example.feature_details_screen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_details_screen.data.model.RemotePhoneDetailsItem
import com.example.feature_details_screen.domain.DetailsRepository
import com.example.feature_details_screen.domain.usecase.LoadDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val loadDetailsUseCase: LoadDetailsUseCase
    ) : ViewModel() {

    private val _details: MutableStateFlow<RemotePhoneDetailsItem?> = MutableStateFlow(null)
    val details:StateFlow<RemotePhoneDetailsItem?> = _details.asStateFlow()

    fun loadDetails(itemId: String) {
        viewModelScope.launch {
            val remoteDetails = loadDetailsUseCase.loadDetails(itemId)
            _details.value = remoteDetails
        }
    }

}