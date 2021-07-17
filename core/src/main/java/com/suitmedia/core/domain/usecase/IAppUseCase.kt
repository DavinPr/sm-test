package com.suitmedia.core.domain.usecase

import com.suitmedia.core.data.Resource
import com.suitmedia.core.domain.usecase.model.EventDomain
import com.suitmedia.core.domain.usecase.model.GuestDomain
import kotlinx.coroutines.flow.Flow

interface IAppUseCase {
    val getGuests : Flow<Resource<List<GuestDomain>>>
    val getEvents : Flow<List<EventDomain>>
}