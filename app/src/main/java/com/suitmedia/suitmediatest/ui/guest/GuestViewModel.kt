package com.suitmedia.suitmediatest.ui.guest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suitmedia.core.data.Resource
import com.suitmedia.core.domain.usecase.IAppUseCase
import com.suitmedia.core.domain.usecase.model.GuestDomain
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class GuestViewModel(private val useCase: IAppUseCase) : ViewModel() {
    private var _getGuest = MutableLiveData<Resource<List<GuestDomain>>>()
    val getGuest : LiveData<Resource<List<GuestDomain>>> get() = _getGuest

    fun requestGuest() {
        viewModelScope.launch {
            useCase.getGuests.collect {
                when (it) {
                    is Resource.Loading -> _getGuest.postValue(Resource.Loading())
                    is Resource.Success -> {
                        if (it.data != null) {
                            _getGuest.postValue(Resource.Success(it.data!!))
                        }
                    }
                    is Resource.Error -> _getGuest.postValue(Resource.Error(it.message ?: "Error"))
                }
            }
        }
    }
}