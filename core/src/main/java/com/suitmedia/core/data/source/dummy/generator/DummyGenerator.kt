package com.suitmedia.core.data.source.dummy.generator

import com.suitmedia.core.data.source.dummy.model.EventModel

object DummyGenerator {
    fun generateDataDummyEvent(): List<EventModel> {
        val list = ArrayList<EventModel>()

        list.add(
            EventModel(
                "@drawable/kartini_day",
                "Kartini Day",
                "2021-04-21"
            )
        )
        list.add(
            EventModel(
                "@drawable/pancasila_day",
                "Pancasila Day",
                "2021-06-01"
            )
        )
        list.add(
            EventModel(
                "@drawable/flag_indonesia",
                "Indonesian Independence Day",
                "2021-08-17"
            )
        )
        list.add(
            EventModel(
                "@drawable/new_year",
                "New Year",
                "2022-01-01"
            )
        )
        return list
    }
}