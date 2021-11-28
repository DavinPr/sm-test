package com.suitmedia.suitmediatest.di

import com.suitmedia.core.domain.usecase.AppInteractor
import com.suitmedia.core.domain.usecase.IAppUseCase
import com.suitmedia.suitmediatest.ui.event.EventViewModel
import com.suitmedia.suitmediatest.ui.guest.GuestViewModel
import com.suitmedia.suitmediatest.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<IAppUseCase> { AppInteractor(get()) }
}

val viewModelModule = module {
    viewModel { GuestViewModel(get()) }
    viewModel { EventViewModel(get()) }
    viewModel { HomeViewModel() }
}