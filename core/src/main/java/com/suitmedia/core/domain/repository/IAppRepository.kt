package com.suitmedia.core.domain.repository

import com.suitmedia.core.data.Resource
import com.suitmedia.core.domain.usecase.model.EventDomain
import com.suitmedia.core.domain.usecase.model.GuestDomain
import kotlinx.coroutines.flow.Flow

interface IAppRepository {
    fun getGuests() : Flow<Resource<List<GuestDomain>>>
    fun getEvents() : Flow<List<EventDomain>>
}