package com.suitmedia.core.utils

import com.suitmedia.core.data.source.dummy.model.EventModel
import com.suitmedia.core.data.source.remote.response.GuestResponse
import com.suitmedia.core.domain.usecase.model.EventDomain
import com.suitmedia.core.domain.usecase.model.GuestDomain

fun GuestResponse.toDomain(index: Int) : GuestDomain = GuestDomain(
    birthdate, name, id,"https://randomuser.me/api/portraits/men/$index.jpg"
)

fun EventModel.toDomain() : EventDomain = EventDomain(
    image, name, date, detail, lat, lng
)