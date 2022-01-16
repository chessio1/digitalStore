package com.example.feature_details_screen.presentation

import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_details_screen.data.model.PhoneDetailsItem
import com.example.feature_details_screen.domain.DetailsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: DetailsRepository) : ViewModel() {

    private val _details: MutableStateFlow<PhoneDetailsItem?> = MutableStateFlow(null)
    val details:StateFlow<PhoneDetailsItem?> = _details.asStateFlow()

    private val _drawables: MutableStateFlow<List<Drawable>?> = MutableStateFlow(null)
    val drawables:StateFlow<List<Drawable>?> = _drawables.asStateFlow()

    fun loadDetails(itemId: Int) {
        viewModelScope.launch {
            val remoteDetails = repository.loadDetails(itemId)
            _details.value = remoteDetails
            _drawables.value = repository.loadDrawables(remoteDetails.images)
        }
    }

}