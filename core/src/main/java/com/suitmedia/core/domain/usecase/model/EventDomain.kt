package com.suitmedia.core.domain.usecase.model

data class EventDomain(
    val image : String,
    val name : String,
    val date : String,
    val detail : String,
    val lat : Double,
    val lng : Double
)
