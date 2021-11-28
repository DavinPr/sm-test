package com.suitmedia.suitmediatest.ui.guest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suitmedia.core.data.Resource
import com.suitmedia.core.domain.usecase.IAppUseCase
import com.suitmedia.core.domain.usecase.model.GuestDomain
import com.suitmedia.suitmediatest.model.Guest
import com.suitmedia.suitmediatest.utils.toPresentation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class GuestViewModel(private val useCase: IAppUseCase) : ViewModel() {
    private val _guest = MutableStateFlow<Resource<List<GuestDomain>>>(Resource.Success(listOf()))
    val guest = _guest.asStateFlow()
    fun requestGuestData() {
        viewModelScope.launch {
            _guest.emitAll(useCase.getGuests)
        }
    }
}