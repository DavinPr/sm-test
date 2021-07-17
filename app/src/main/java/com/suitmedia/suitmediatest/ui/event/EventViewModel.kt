package com.suitmedia.suitmediatest.ui.event

import androidx.lifecycle.ViewModel
import com.suitmedia.core.domain.usecase.IAppUseCase

class EventViewModel(useCase: IAppUseCase) : ViewModel() {
    val getEvents = useCase.getEvents
}