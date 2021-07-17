package com.suitmedia.core.domain.usecase

import com.suitmedia.core.data.Resource
import com.suitmedia.core.domain.repository.IAppRepository
import com.suitmedia.core.domain.usecase.model.EventDomain
import com.suitmedia.core.domain.usecase.model.GuestDomain
import kotlinx.coroutines.flow.Flow

class AppInteractor(private val repository: IAppRepository) : IAppUseCase {
    override val getGuests: Flow<Resource<List<GuestDomain>>>
        get() = repository.getGuests()

    override val getEvents: Flow<List<EventDomain>>
        get() = repository.getEvents()
}