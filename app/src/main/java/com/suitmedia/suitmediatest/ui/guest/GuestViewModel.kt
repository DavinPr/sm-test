package com.suitmedia.suitmediatest.ui.guest

import androidx.lifecycle.ViewModel
import com.suitmedia.core.domain.usecase.IAppUseCase

class GuestViewModel(useCase: IAppUseCase) : ViewModel() {
    val getGuest = useCase.getGuests
}