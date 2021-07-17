package com.suitmedia.core.data.source.dummy

import com.suitmedia.core.data.source.dummy.generator.DummyGenerator

class DummyDataSource {
    fun getEvents() = DummyGenerator.generateDataDummyEvent()
}