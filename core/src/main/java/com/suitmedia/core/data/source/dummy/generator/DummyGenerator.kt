package com.suitmedia.core.data.source.dummy.generator

import com.suitmedia.core.data.source.dummy.model.EventModel

object DummyGenerator {
    fun generateDataDummyEvent(): List<EventModel> {
        val list = ArrayList<EventModel>()

        list.add(
            EventModel(
                "@drawable/kartini_day",
                "Kartini Day",
                "2021-04-21",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                -7.774945,
                110.386213
            )
        )
        list.add(
            EventModel(
                "@drawable/pancasila_day",
                "Pancasila Day",
                "2021-06-01",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                -7.771715,
                110.392554
            )
        )
        list.add(
            EventModel(
                "@drawable/flag_indonesia",
                "Indonesian Independence Day",
                "2021-08-17",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                -7.784251,
                110.390550
            )
        )
        list.add(
            EventModel(
                "@drawable/new_year",
                "New Year",
                "2022-01-01",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                -7.778116,
                110.384598
            )
        )
        return list
    }
}