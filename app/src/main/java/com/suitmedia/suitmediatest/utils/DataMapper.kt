package com.suitmedia.suitmediatest.utils

import com.suitmedia.core.domain.usecase.model.EventDomain
import com.suitmedia.core.domain.usecase.model.GuestDomain
import com.suitmedia.suitmediatest.model.Event
import com.suitmedia.suitmediatest.model.Guest

fun GuestDomain.toPresentation() : Guest = Guest(
    id, name, birthdate, image
)

fun EventDomain.toPresentation() : Event = Event(
    image, name, date, detail, lat, lng
)