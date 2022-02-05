package com.example.feature_details_screen.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.exceptions.NoDataException
import com.example.feature_details_screen.data.model.RemotePhoneDetailsItem
import com.example.feature_details_screen.domain.DetailsRepository
import com.example.feature_details_screen.domain.usecase.LoadDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class DetailsViewModel(
    private val loadDetailsUseCase: LoadDetailsUseCase
) : ViewModel() {

    private val _details: MutableLiveData<RemotePhoneDetailsItem?> = MutableLiveData()
    val details: LiveData<RemotePhoneDetailsItem?>
        get() = _details

    fun loadDetails(itemId: String) {
        loadDetailsUseCase.loadDetails("61dd5a78d4fd1466000a280a"){
            _details.postValue(it)
        }
//        val remoteDetails = loadDetailsUseCase.loadDetails(itemId) ?: return
//        remoteDetails.subscribe({
//            _details.postValue(it)
//        },{
//            Timber.d(it)
//        })
    }

}